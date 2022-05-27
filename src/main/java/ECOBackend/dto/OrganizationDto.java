package ECOBackend.dto;

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
    private List<UserDto> users;
    private List<PlaceDto> places;
}
