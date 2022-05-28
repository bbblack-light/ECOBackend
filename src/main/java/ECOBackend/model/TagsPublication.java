package ECOBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags_publication")
@Getter
@Setter
public class TagsPublication extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tags tag;
}