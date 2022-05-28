package ECOBackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
public class Tags extends BaseEntity {
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tag")
    private List<TagsPlace> places;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tag")
    private List<TagsPublication> publications;
}