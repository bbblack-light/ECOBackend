package ECOBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "place")
@Data
public class Place extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Organization author;
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<TagsPlace> tags;
}