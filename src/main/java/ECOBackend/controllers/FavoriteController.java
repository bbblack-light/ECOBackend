package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PublicationDto;
import ECOBackend.services.FavoriteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Favorites"})
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("{userId}/{publicationId}")
    public OperationResponse update(@PathVariable("userId") String userId, @PathVariable("publicationId") Long publicationId) {
        return favoriteService.update(userId, publicationId);
    }

    @DeleteMapping("/{userId}/{publicationId}")
    public OperationResponse delete(@PathVariable("userId") String userId, @PathVariable("publicationId") Long publicationId) {
        return favoriteService.delete(userId, publicationId);
    }

    @DeleteMapping("/{id}")
    public OperationResponse deleteById(@PathVariable("id") Long id) {
        return favoriteService.delete(id);
    }

    @GetMapping("/allByUser/{userId}")
    public List<PublicationDto> getFavoritesByUser(@PathVariable("userId") String userId) {
        return favoriteService.getAllByUser(userId);
    }
}
