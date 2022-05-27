package ECOBackend.controllers;


import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.OrganizationDto;
import ECOBackend.dto.PublicationDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Publications"})
@RequestMapping("/publication")
public class PublicationController {

    @PostMapping("")
    public PublicationDto update(@RequestBody PublicationDto dto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<PublicationDto> getAll() {
        return new ArrayList<PublicationDto>();
    }

    @GetMapping("/allByUser/{userId}")
    public List<PublicationDto> getByUser(@PathVariable("userId") String userId) {
        return null;
    }

    @GetMapping("/allByOrganization/{organizationId}")
    public List<PublicationDto> getByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        return null;
    }

    @GetMapping("/allByTagId/{tagId}")
    public List<PublicationDto> getAllByTagId(@PathVariable("tagId") Long tagId) {
        return null;
    }

    @GetMapping("/allByTagName/{tagName}")
    public List<PublicationDto> getAllByTagName(@PathVariable("tagName") String tagName) {
        return null;
    }

    @GetMapping("/allFavoritesByUser/{userId}")
    public List<PublicationDto> getFavoritesByUser(@PathVariable("userId") String userId) {
        return null;
    }

    @GetMapping("/getById/{Id}")
    public PublicationDto getById(@PathVariable("Id") Long Id) {
        return null;
    }
}
