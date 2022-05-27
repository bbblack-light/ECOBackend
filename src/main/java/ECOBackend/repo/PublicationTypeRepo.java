package ECOBackend.repo;

import ECOBackend.model.PublicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationTypeRepo extends JpaRepository<PublicationType, Long> {
}
