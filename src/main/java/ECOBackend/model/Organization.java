package ECOBackend.model;

import ECOBackend.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "organization")
@Data
public class Organization extends BaseEntity {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String vkURL;
    private String instURL;
    private String tgURL;
    @OneToMany(mappedBy = "organization")
    private List<User> users;

    @OneToMany(mappedBy = "author")
    private List<Place> places;
}
