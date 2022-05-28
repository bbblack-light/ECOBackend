package ECOBackend.model;

import ECOBackend.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Organization extends BaseEntity {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String vkURL;
    private String instURL;
    private String tgURL;
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Place> places;
}
