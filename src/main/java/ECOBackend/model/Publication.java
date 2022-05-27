package ECOBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "publication")
@Data
public class Publication extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PublicationType type;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Organization author;
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Images> images;
    private String name;
    private LocalDate publicationDate;
    private LocalDate eventDate;
    private String text;
}