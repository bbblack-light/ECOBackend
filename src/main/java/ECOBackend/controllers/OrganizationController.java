package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.OrganizationDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = {"Organization"})
@RequestMapping("/organization")
public class OrganizationController {

    @PostMapping("")
    public OrganizationDto update(@RequestBody OrganizationDto dto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/{id}")
    public OrganizationDto getById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/all")
    public List<OrganizationDto> getAll() {
        return new ArrayList<OrganizationDto>();
    }

    @GetMapping("/byUser/{userId}")
    public OrganizationDto getByUser(@PathVariable("userId") String userId) {
        return null;
    }
}
