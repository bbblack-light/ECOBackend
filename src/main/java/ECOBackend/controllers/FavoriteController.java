package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.FavoriteDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"Favorites"})
@RequestMapping("/favorites")
public class FavoriteController {

    @PostMapping("")
    public FavoriteDto update(@RequestBody FavoriteDto dto) {
        return null;
    }

    @DeleteMapping("/{userId}/{publicationId}")
    public OperationResponse delete(@PathVariable("userId") String userId, @PathVariable("publicationId") Long publicationId) {
        return null;
    }

    @DeleteMapping("/{id}")
    public OperationResponse deleteById(@PathVariable("id") Long id) {
        return null;
    }
}
