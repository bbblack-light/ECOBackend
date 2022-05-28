package ECOBackend.controllers;


import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PublicationDto;
import ECOBackend.services.PublicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Publications"})
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("")
    public PublicationDto update(@RequestBody PublicationDto dto) {
        return publicationService.update(dto);
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return publicationService.delete(id);
    }

    @GetMapping("/all")
    public List<PublicationDto> getAll() {
        return publicationService.getAll();
    }

    @GetMapping("/allByOrganization/{organizationId}")
    public List<PublicationDto> getByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        return publicationService.getByOrganization(organizationId);
    }

    @GetMapping("/allByTagId/{tagId}")
    public List<PublicationDto> getAllByTagId(@PathVariable("tagId") Long tagId) {
        return publicationService.getAllByTagId(tagId);
    }

    @GetMapping("/allByTagName/{tagName}")
    public List<PublicationDto> getAllByTagName(@PathVariable("tagName") String tagName) {
        return publicationService.getAllByTagName(tagName);
    }

    @GetMapping("/getById/{Id}")
    public PublicationDto getById(@PathVariable("Id") Long id) {
        return publicationService.getById(id);
    }
}
