package ECOBackend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publication_type")
public class PublicationType extends BaseEntity{
    private String name;
}