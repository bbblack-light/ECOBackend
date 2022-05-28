package ECOBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags_place")
@Getter
@Setter
public class TagsPlace extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tags tag;
}