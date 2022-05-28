package ECOBackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PublicationDto extends BaseDto{
    private List<TagDto> tags;
    private List<ImagesDto> images;
    private PublicationTypeDto publicationType;
    private OrganizationWithoutUsersDto author;
    private Long countInFavorites;
    private PlaceDto place;
    private String name;
    private LocalDate publicationDate;
    private LocalDate eventDate;
    private String text;
}
