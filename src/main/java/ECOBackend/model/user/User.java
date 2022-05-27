package ECOBackend.model.user;

import ECOBackend.model.Favorite;
import ECOBackend.model.Organization;
import ECOBackend.model.TagsPlace;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "m_User")
@Data
public class User {
    @Id
    private String userId;
    private String password = "";
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Favorite> favorites;

    public User() {
        new User(
                "new",
                "new",
                "new",
                "new",
                "new",
                "",
                Role.USER);
    }

    public User(String userId,
                String password,
                String firstName,
                String lastName,
                String patronymic,
                String email,
                Role role) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.role = role;
    }

    public String getFullName() {
        return this.firstName + this.lastName + this.patronymic;
    }
}

