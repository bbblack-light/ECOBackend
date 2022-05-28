package ECOBackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteDto {
    private String userId;
    private Long publicationId;
}
