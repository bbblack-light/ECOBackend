package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.OrganizationDto;
import ECOBackend.services.OrganizationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Organization"})
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("")
    public OrganizationDto update(@RequestBody OrganizationDto dto) {
        return organizationService.update(dto);
    }

    @DeleteMapping("/{id}")
    public OperationResponse delete(@PathVariable("id") Long id) {
        return organizationService.delete(id);
    }

    @GetMapping("/{id}")
    public OrganizationDto getById(@PathVariable("id") Long id) {
        return organizationService.getById(id);
    }

    @GetMapping("/all")
    public List<OrganizationDto> getAll() {
        return organizationService.getAll();
    }

    @GetMapping("/byUser/{userId}")
    public OrganizationDto getByUser(@PathVariable("userId") String userId) {
        return organizationService.getByUser(userId);
    }
}
