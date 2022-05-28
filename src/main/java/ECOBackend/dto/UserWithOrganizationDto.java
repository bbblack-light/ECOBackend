package ECOBackend.dto;

import ECOBackend.model.user.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithOrganizationDto {
    private String userId;
    private String password = "";
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private Role role;
    private String phoneNumber;
    private OrganizationWithoutUsersDto organization;
}
