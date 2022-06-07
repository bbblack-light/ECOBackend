package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.ImagesDto;
import ECOBackend.dto.PublicationDto;
import ECOBackend.dto.TagDto;
import ECOBackend.model.*;
import ECOBackend.repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    private final PublicationRepo publicationRepo;
    private final PublicationTagRepo publicationTagRepo;
    private final OrganizationRepo organizationRepo;
    private final TagsRepo tagsRepo;
    private final PlaceRepo placeRepo;
    private final ImagesRepo imagesRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PublicationService(PublicationRepo publicationRepo, PublicationTagRepo publicationTagRepo, OrganizationRepo organizationRepo, TagsRepo tagsRepo, PlaceRepo placeRepo, ImagesRepo imagesRepo, ModelMapper modelMapper) {
        this.publicationRepo = publicationRepo;
        this.publicationTagRepo = publicationTagRepo;
        this.organizationRepo = organizationRepo;
        this.tagsRepo = tagsRepo;
        this.placeRepo = placeRepo;
        this.imagesRepo = imagesRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PublicationDto update(PublicationDto dto) {
        Publication fromrepo;
        if (dto.getId() == null){
            Publication publication = modelMapper.map(dto, Publication.class);
            publication.setTags(null);

            fromrepo = publicationRepo.save(publication);
            fromrepo.setTags(new ArrayList<>());
        }
        else {
            if (!publicationRepo.existsById(dto.getId())) throw new NotFoundException("Публикация не существует");
            fromrepo = publicationRepo.getById(dto.getId());
        }

        Optional<Organization> optionalOrganization = organizationRepo.findById(dto.getAuthor().getId());
        if(!optionalOrganization.isPresent()) throw new NotFoundException("Организация не сущетсвует");
        fromrepo.setAuthor(optionalOrganization.get());

        if (dto.getPlace()!=null) {
            Optional<Place> placeOptional = placeRepo.findById(dto.getPlace().getId());
            if(!placeOptional.isPresent()) throw new NotFoundException("Место не сущетсвует");
            fromrepo.setPlace(placeOptional.get());
        }

        if (CollectionUtils.isEmpty(dto.getTags()))
            dto.setTags(new ArrayList<>());
        if (CollectionUtils.isEmpty(fromrepo.getTags()))
            fromrepo.setTags(new ArrayList<>());

        List<TagsPublication> remove = new ArrayList<>();
        //remove elements if dto dont have it
        for (TagsPublication tagsPublication : fromrepo.getTags()) {
            boolean any = dto.getTags().stream()
                    .anyMatch(dtoTag -> Objects.equals(dtoTag.getId(), tagsPublication.getTag().getId()));
            if (!any) {
                publicationTagRepo.delete(tagsPublication);
                remove.add(tagsPublication);
            }
        }
        fromrepo.getTags().removeAll(remove);

        //add elements if repo dont have it
        for (TagDto dtoTags : dto.getTags()) {
            boolean any = fromrepo.getTags().stream()
                    .anyMatch(repoTag -> Objects.equals(repoTag.getTag().getId(), dtoTags.getId()));
            if (!any) {
                TagsPublication cd = new TagsPublication();
                cd.setPublication(fromrepo);

                Optional<Tags> tagsOptional = tagsRepo.findById(dtoTags.getId());
                if (!tagsOptional.isPresent()) throw new NotFoundException("Тэг не существует");

                cd.setTag(tagsOptional.get());
                fromrepo.getTags().add(cd);
            }
        }

        //images processing
        List<Images> removeUserFromOrganization = new ArrayList<>();
        for (Images images : fromrepo.getImages()) {
            if (dto.getImages().stream()
                    .noneMatch(imagesDto -> Objects.equals(imagesDto.getId(), images.getId()))) {
                removeUserFromOrganization.add(images);
                imagesRepo.delete(images);
            }
        }
        fromrepo.getImages().removeAll(removeUserFromOrganization);


        for (ImagesDto imagesDto : dto.getImages()) {
            if (dto.getImages().stream()
                    .noneMatch(u -> Objects.equals(u.getId(), imagesDto.getId()))) {
                Images im = new Images();
                im.setPublication(fromrepo);
                im.setURL(imagesDto.getURL());
                fromrepo.getImages().add(im);
            }
        }
        Publication save = publicationRepo.save(fromrepo);
        return modelMapper.map(publicationRepo.getById(save.getId()), PublicationDto.class);
    }

    @Transactional
    public OperationResponse delete(long id) {
        if (!publicationRepo.existsById(id)) {
            return new OperationResponse("Публикация не существует");
        }
        Publication publication = publicationRepo.getById(id);
        publicationRepo.delete(publication);
        return new OperationResponse("ok");
    }

    @Transactional
    public PublicationDto getById(long id) {
        if (!publicationRepo.existsById(id)) {
            throw new NotFoundException("Место не существует");
        }
        return modelMapper.map(publicationRepo.getById(id), PublicationDto.class);
    }

    @Transactional
    public List<PublicationDto> getAll() {
        return publicationRepo.findAll().stream()
                .map(x -> modelMapper.map(x, PublicationDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PublicationDto> getByOrganization(Long organizationId) {
        Optional<Organization> optionalOrganization = organizationRepo.findById(organizationId);
        if(!optionalOrganization.isPresent()) throw new NotFoundException("Организация не сущетсвует");

        return publicationRepo.findAllByAuthor(optionalOrganization.get()).stream()
                .map(x -> modelMapper.map(x, PublicationDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PublicationDto> getAllByTagId(Long tagId) {
        return publicationRepo.findAll().stream()
                .filter(place -> place.getTags().stream().anyMatch(tagsPlace -> tagsPlace.getTag().getId().equals(tagId)))
                .map(x -> modelMapper.map(x, PublicationDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PublicationDto> getAllByTagName(String tagName) {
        return publicationRepo.findAll().stream()
                .filter(place -> place.getTags().stream().anyMatch(tagsPlace -> tagsPlace.getTag().getName().equals(tagName)))
                .map(x -> modelMapper.map(x, PublicationDto.class))
                .collect(Collectors.toList());
    }
}
