package ECOBackend.dto;

import lombok.Data;

@Data
public class FavoriteDto {
    private String userId;
    private Long publicationId;
}
