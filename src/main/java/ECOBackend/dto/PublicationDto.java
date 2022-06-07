package ECOBackend.dto;

import ECOBackend.model.PublicationType;

import java.time.LocalDate;
import java.util.List;

public class PublicationDto extends BaseDto{
    private List<TagDto> tags;
    private List<ImagesDto> images;
    private PublicationType publicationType;
    private OrganizationWithoutUsersDto author;
    private Long countInFavorites;
    private PlaceDto place;
    private String name;
    private LocalDate publicationDate;
    private LocalDate eventDate;
    private String text;

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public List<ImagesDto> getImages() {
        return images;
    }

    public void setImages(List<ImagesDto> images) {
        this.images = images;
    }

    public OrganizationWithoutUsersDto getAuthor() {
        return author;
    }

    public void setAuthor(OrganizationWithoutUsersDto author) {
        this.author = author;
    }

    public Long getCountInFavorites() {
        return countInFavorites;
    }

    public void setCountInFavorites(Long countInFavorites) {
        this.countInFavorites = countInFavorites;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }
}
