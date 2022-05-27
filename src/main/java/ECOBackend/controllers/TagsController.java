package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.TagDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Tags"})
@RequestMapping("/tags")
public class TagsController {

    @PostMapping("")
    public TagDto update(@RequestBody TagDto dto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<TagDto> getAll() {
        return new ArrayList<TagDto>();
    }
}
