package ECOBackend.dto;

import ECOBackend.model.user.Role;

public class UserWithOrganizationDto {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public OrganizationWithoutUsersDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationWithoutUsersDto organization) {
        this.organization = organization;
    }

    private String userId;
    private String password = "";
    private String firstName;
    private String email;
    private Role role;
    private String phoneNumber;
    private OrganizationWithoutUsersDto organization;
}
