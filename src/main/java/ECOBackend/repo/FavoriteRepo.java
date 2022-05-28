package ECOBackend.repo;

import ECOBackend.model.Favorite;
import ECOBackend.model.Publication;
import ECOBackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByPublicationAndUser(Publication p, User user);
    List<Favorite> findAllByUser(User user);
}
