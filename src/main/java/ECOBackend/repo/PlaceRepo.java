package ECOBackend.repo;

import ECOBackend.model.Organization;
import ECOBackend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long> {
    List<Place> findAllByAuthor(Organization organization);
}
