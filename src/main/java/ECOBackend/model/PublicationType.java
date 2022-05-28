package ECOBackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "publication_type")
@Getter
@Setter
public class PublicationType extends BaseEntity{
    private String name;
}