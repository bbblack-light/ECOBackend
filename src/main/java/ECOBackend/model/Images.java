package ECOBackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "images")
@Data
public class Images extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
    private String URL;
}
