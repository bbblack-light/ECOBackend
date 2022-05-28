package ECOBackend.controllers;


import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PlaceDto;
import ECOBackend.services.PlaceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Places"})
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("")
    public PlaceDto update(@RequestBody PlaceDto dto) {
        return placeService.update(dto);
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return placeService.delete(id);
    }

    @GetMapping("/{id}")
    public PlaceDto getById(@PathVariable("id") Long id) {
        return placeService.getById(id);
    }

    @GetMapping("/all")
    public List<PlaceDto> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/allByOrganization/{organizationId}")
    public List<PlaceDto> getAllByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        return placeService.getByOrganization(organizationId);
    }

    @GetMapping("/allByTag/{tagId}")
    public List<PlaceDto> getAllByTagId(@PathVariable("tagId") Long tagId) {
        return placeService.getAllByTagId(tagId);
    }

    @GetMapping("/allByTagName/{tagName}")
    public List<PlaceDto> getAllByTagName(@PathVariable("tagName") String tagName) {
        return placeService.getAllByTagName(tagName);
    }
}
