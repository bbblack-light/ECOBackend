package ECOBackend.dto;

import ECOBackend.model.user.Role;
import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String password = "";
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private Role role;
    private String phoneNumber;
    private OrganizationDto organization;
}
