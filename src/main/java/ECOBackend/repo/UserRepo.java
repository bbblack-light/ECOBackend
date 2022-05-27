package ECOBackend.repo;

import ECOBackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findOneByUserId(String userId);

    boolean existsByUserId(String userId);

    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}
