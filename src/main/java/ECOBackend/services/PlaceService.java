package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PlaceDto;
import ECOBackend.dto.TagDto;
import ECOBackend.model.Organization;
import ECOBackend.model.Place;
import ECOBackend.model.Tags;
import ECOBackend.model.TagsPlace;
import ECOBackend.repo.OrganizationRepo;
import ECOBackend.repo.PlaceRepo;
import ECOBackend.repo.TagPlaceRepo;
import ECOBackend.repo.TagsRepo;
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
public class PlaceService {
    private final PlaceRepo placeRepo;
    private final OrganizationRepo organizationRepo;
    private final TagsRepo tagsRepo;
    private final TagPlaceRepo tagPlaceRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PlaceService(PlaceRepo placeRepo, OrganizationRepo organizationRepo, TagsRepo tagsRepo, TagPlaceRepo tagPlaceRepo, ModelMapper modelMapper) {
        this.placeRepo = placeRepo;
        this.organizationRepo = organizationRepo;
        this.tagsRepo = tagsRepo;
        this.tagPlaceRepo = tagPlaceRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PlaceDto update(PlaceDto dto) {
        Place fromrepo;
        if (dto.getId() == null){
            Place place = modelMapper.map(dto, Place.class);
            place.setTags(null);
            fromrepo = placeRepo.save(place);
            fromrepo.setTags(new ArrayList<>());
        }
        else {
            if (!placeRepo.existsById(dto.getId())) throw new NotFoundException("Место не существует");
            fromrepo = placeRepo.getById(dto.getId());
        }
        Optional<Organization> optionalOrganization = organizationRepo.findById(dto.getAuthor().getId());
        if(!optionalOrganization.isPresent()) throw new NotFoundException("Организация не сущетсвует");
        fromrepo.setAuthor(optionalOrganization.get());

        if (CollectionUtils.isEmpty(dto.getTags()))
            dto.setTags(new ArrayList<>());
        if (CollectionUtils.isEmpty(fromrepo.getTags()))
            fromrepo.setTags(new ArrayList<>());

        List<TagsPlace> remove = new ArrayList<>();
        //remove elements if dto dont have it
        for (TagsPlace tagsPlace : fromrepo.getTags()) {
            boolean any = dto.getTags().stream()
                    .anyMatch(dtoTag -> Objects.equals(dtoTag.getId(), tagsPlace.getTag().getId()));
            if (!any) {
                tagPlaceRepo.delete(tagsPlace);
                remove.add(tagsPlace);
            }
        }
        fromrepo.getTags().removeAll(remove);

        //add elements if repo dont have it
        for (TagDto dtoTags : dto.getTags()) {
            boolean any = fromrepo.getTags().stream()
                    .anyMatch(repoTag -> Objects.equals(repoTag.getTag().getId(), dtoTags.getId()));
            if (!any) {
                TagsPlace cd = new TagsPlace();
                cd.setPlace(fromrepo);

                Optional<Tags> tagsOptional = tagsRepo.findById(dtoTags.getId());
                if (!tagsOptional.isPresent()) throw new NotFoundException("Тэг не существует");

                cd.setTag(tagsOptional.get());
                fromrepo.getTags().add(cd);
            }
        }
        Place save = placeRepo.save(fromrepo);
        return modelMapper.map(placeRepo.getById(save.getId()), PlaceDto.class);
    }

    @Transactional
    public OperationResponse delete(long id) {
        if (!placeRepo.existsById(id)) {
            return new OperationResponse("Место не существует");
        }
        Place place = placeRepo.getById(id);
        placeRepo.delete(place);
        return new OperationResponse("ok");
    }

    @Transactional
    public PlaceDto getById(long id) {
        if (!placeRepo.existsById(id)) {
            throw new NotFoundException("Место не существует");
        }
        return modelMapper.map(placeRepo.getById(id), PlaceDto.class);
    }

    @Transactional
    public List<PlaceDto> getAll() {
        return placeRepo.findAll().stream()
                .map(x -> modelMapper.map(x, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PlaceDto> getByOrganization(Long organizationId) {
        Optional<Organization> optionalOrganization = organizationRepo.findById(organizationId);
        if(!optionalOrganization.isPresent()) throw new NotFoundException("Организация не сущетсвует");

        return placeRepo.findAllByAuthor(optionalOrganization.get()).stream()
                .map(x -> modelMapper.map(x, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PlaceDto> getAllByTagId(Long tagId) {
        return placeRepo.findAll().stream()
                .filter(place -> place.getTags().stream().anyMatch(tagsPlace -> tagsPlace.getTag().getId().equals(tagId)))
                .map(x -> modelMapper.map(x, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PlaceDto> getAllByTagName(String tagName) {
        return placeRepo.findAll().stream()
                .filter(place -> place.getTags().stream().anyMatch(tagsPlace -> tagsPlace.getTag().getName().equals(tagName)))
                .map(x -> modelMapper.map(x, PlaceDto.class))
                .collect(Collectors.toList());
    }
}
