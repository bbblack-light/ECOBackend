package ECOBackend.repo;

import ECOBackend.model.TagsPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagPlaceRepo extends JpaRepository<TagsPlace, Long> {
}
