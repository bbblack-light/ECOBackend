package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.TagDto;
import ECOBackend.model.Tags;
import ECOBackend.repo.TagsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagsRepo tagsRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public TagService(TagsRepo tagsRepo, ModelMapper modelMapper) {
        this.tagsRepo = tagsRepo;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public TagDto update(TagDto dto) {
        return modelMapper.map(tagsRepo.save(modelMapper.map(dto, Tags.class)), TagDto.class);
    }

    @Transactional
    public OperationResponse deleteById(Long id) {
        if (!tagsRepo.existsById(id)) {
            return new OperationResponse("Тэг не существует");
        }
        Tags tag = tagsRepo.getById(id);
        tagsRepo.delete(tag);
        return new OperationResponse("ok");
    }

    @Transactional
    public TagDto getById(long id) {
        if (!tagsRepo.existsById(id)) {
            throw new NotFoundException("Тэг не существует");
        }
        return modelMapper.map(tagsRepo.getById(id), TagDto.class);
    }

    @Transactional
    public List<TagDto> getAll() {
        return tagsRepo.findAll().stream()
                .map(x -> modelMapper.map(x, TagDto.class))
                .collect(Collectors.toList());
    }
}
