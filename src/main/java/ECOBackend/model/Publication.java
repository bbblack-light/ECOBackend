package ECOBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "publication")
@Getter
@Setter
public class Publication extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PublicationType type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
    private List<TagsPublication> tags;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Organization author;
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Images> images;
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Favorite> favorites;
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Entry> entries;
    private String name;
    private LocalDate publicationDate;
    private LocalDate eventDate;
//    @Column(name="text", length = 2048)
    @Lob
    private String text;
}