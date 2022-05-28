package ECOBackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceDto extends BaseDto {
    private OrganizationWithoutUsersDto author;
    private String name;
    private String address;
    private String description;
    private List<TagDto> tags;
}
