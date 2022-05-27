package ECOBackend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PublicationDto {
    private PublicationTypeDto publicationType;
    private List<TagDto> tags;
    private PlaceDto place;
    private List<ImagesDto> images;
    private String name;
    private LocalDate publicationDate;
    private LocalDate eventDate;
    private String text;
}
