package ECOBackend.repo;

import ECOBackend.model.Entry;
import ECOBackend.model.Publication;
import ECOBackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntryRepo extends JpaRepository <Entry, Long> {
    void deleteByUserAndPublication(User u, Publication p);
}
