package ECOBackend.repo;

import ECOBackend.model.Organization;
import ECOBackend.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {
    List<Publication> findAllByAuthor(Organization organization);
}
