package ECOBackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceDto extends BaseDto {
    private Long authorId;
    private String name;
    private String address;
    private String description;
    private List<TagDto> tags;
}
