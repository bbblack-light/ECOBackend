package ECOBackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tags extends BaseEntity {
    private String name;
    @OneToMany
    private List<TagsPlace> places;
    @OneToMany
    private List<TagsPublication> publications;
}