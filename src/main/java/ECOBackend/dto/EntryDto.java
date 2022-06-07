package ECOBackend.dto;

import ECOBackend.model.EntryStatus;

public class EntryDto extends BaseDto {
    private UserDto user;
    private PublicationDto publication;
    private EntryStatus status;


    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PublicationDto getPublication() {
        return publication;
    }

    public void setPublication(PublicationDto publication) {
        this.publication = publication;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }
}
