package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PublicationTypeDto;
import ECOBackend.dto.TagDto;
import ECOBackend.model.PublicationType;
import ECOBackend.model.Tags;
import ECOBackend.repo.PublicationTypeRepo;
import ECOBackend.repo.TagsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationTypeService {
    private final PublicationTypeRepo publicationTypeRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PublicationTypeService(PublicationTypeRepo publicationTypeRepo, ModelMapper modelMapper) {
        this.publicationTypeRepo = publicationTypeRepo;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public PublicationTypeDto update(PublicationTypeDto dto) {
        return modelMapper.map(publicationTypeRepo.save(modelMapper.map(dto, PublicationType.class)), PublicationTypeDto.class);
    }

    @Transactional
    public OperationResponse deleteById(Long id) {
        if (!publicationTypeRepo.existsById(id)) {
            return new OperationResponse("Тип публикации не существует");
        }
        PublicationType publicationType = publicationTypeRepo.getById(id);
        publicationTypeRepo.delete(publicationType);
        return new OperationResponse("ok");
    }

    @Transactional
    public PublicationTypeDto getById(long id) {
        if (!publicationTypeRepo.existsById(id)) {
            throw new NotFoundException("Тип публикации не существует");
        }
        return modelMapper.map(publicationTypeRepo.getById(id), PublicationTypeDto.class);
    }

    @Transactional
    public List<PublicationTypeDto> getAll() {
        return publicationTypeRepo.findAll().stream()
                .map(x -> modelMapper.map(x, PublicationTypeDto.class))
                .collect(Collectors.toList());
    }
}
