package ECOBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "place")
@Getter
@Setter
public class Place extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Organization author;
    private String name;
    private String address;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", fetch = FetchType.LAZY)
    private List<TagsPlace> tags;
}