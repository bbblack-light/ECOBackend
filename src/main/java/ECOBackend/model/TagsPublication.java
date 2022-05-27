package ECOBackend.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags_publication")
public class TagsPublication extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication place;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tag;
}