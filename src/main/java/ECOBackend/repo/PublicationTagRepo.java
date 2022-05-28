package ECOBackend.repo;

import ECOBackend.model.TagsPlace;
import ECOBackend.model.TagsPublication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationTagRepo extends JpaRepository<TagsPublication, Long> {
}
