package ECOBackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationWithoutUsersDto extends BaseDto {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String vkURL;
    private String instURL;
    private String tgURL;
}
