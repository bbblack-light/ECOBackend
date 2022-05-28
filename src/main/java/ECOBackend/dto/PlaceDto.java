package ECOBackend.dto;

import java.util.List;

public class PlaceDto extends BaseDto {
    private OrganizationWithoutUsersDto author;
    private String name;
    private String address;
    private String description;
    private List<TagDto> tags;

    public OrganizationWithoutUsersDto getAuthor() {
        return author;
    }

    public void setAuthor(OrganizationWithoutUsersDto author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}
