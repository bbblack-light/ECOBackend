package ECOBackend.dto;

import ECOBackend.model.Place;
import lombok.Data;

import java.util.List;

@Data
public class OrganizationDto extends BaseDto {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String vkURL;
    private String instURL;
    private String tgURL;
    private String userId;
    private List<PlaceDto> places;
}
