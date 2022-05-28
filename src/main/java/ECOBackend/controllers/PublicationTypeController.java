package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PublicationTypeDto;
import ECOBackend.services.PublicationTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Publication types"})
@RequestMapping("/publication_type")
public class PublicationTypeController {
    private final PublicationTypeService publicationTypeService;

    @Autowired
    public PublicationTypeController(PublicationTypeService publicationTypeService) {
        this.publicationTypeService = publicationTypeService;
    }

    @PostMapping("")
    public PublicationTypeDto update(@RequestBody PublicationTypeDto dto) {
        return publicationTypeService.update(dto);
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return publicationTypeService.deleteById(id);
    }

    @GetMapping("/{id}")
    public PublicationTypeDto getById(@PathVariable("id") Long id) {
        return publicationTypeService.getById(id);
    }

    @GetMapping("/all")
    public List<PublicationTypeDto> getAll() {
        return publicationTypeService.getAll();
    }
}
