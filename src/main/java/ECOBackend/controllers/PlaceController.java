package ECOBackend.controllers;


import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PlaceDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Places"})
@RequestMapping("/place")
public class PlaceController {

    @PostMapping("")
    public PlaceDto update(@RequestBody PlaceDto dto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}")
    public PlaceDto getById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<PlaceDto> getAll() {
        return new ArrayList<PlaceDto>();
    }

    @GetMapping("/allByOrganization/{organizationId}")
    public List<PlaceDto> getAllByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        return new ArrayList<PlaceDto>();
    }

    @GetMapping("/allByTag/{tagId}")
    public List<PlaceDto> getAllByTagId(@PathVariable("tagId") Long tagId) {
        return new ArrayList<PlaceDto>();
    }

    @GetMapping("/allByTagName/{tagName}")
    public List<PlaceDto> getAllByTagName(@PathVariable("tagName") String tagName) {
        return new ArrayList<PlaceDto>();
    }
}
