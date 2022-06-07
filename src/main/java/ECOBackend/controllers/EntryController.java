package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.EntryDto;
import ECOBackend.dto.OrganizationDto;
import ECOBackend.dto.PublicationDto;
import ECOBackend.services.EntryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Entry"})
@RequestMapping("/entry")
public class EntryController {
    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("")
    public EntryDto update(@RequestBody EntryDto dto) {
        return entryService.update(dto);
    }

    @DeleteMapping("/{userId}/{publicationId}")
    public OperationResponse delete(@PathVariable("userId") String userId, @PathVariable("publicationId") Long publicationId) {
        return entryService.delete(userId, publicationId);
    }

    @GetMapping("/allByUser/{userId}")
    public List<EntryDto> getFavoritesByUser(@PathVariable("userId") String userId) {
        return entryService.getAllByUser(userId);
    }

    @GetMapping("/allByPublication/{publicationId}")
    public List<EntryDto> getFavoritesByUser(@PathVariable("publicationId") Long publicationId) {
        return entryService.getAllByPublication(publicationId);
    }
}
