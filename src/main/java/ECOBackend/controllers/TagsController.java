package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.TagDto;
import ECOBackend.services.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Tags"})
@RequestMapping("/tags")
public class TagsController {
    private final TagService tagService;

    @Autowired
    public TagsController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("")
    public TagDto update(@RequestBody TagDto dto) {
        return tagService.update(dto);
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return tagService.deleteById(id);
    }

    @GetMapping("/{id}")
    public TagDto getById(@PathVariable("id") Long id) {
        return tagService.getById(id);
    }

    @GetMapping("/all")
    public List<TagDto> getAll() {
        return tagService.getAll();
    }
}
