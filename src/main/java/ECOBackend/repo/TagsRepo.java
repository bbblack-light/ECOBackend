package ECOBackend.repo;

import ECOBackend.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepo extends JpaRepository<Tags, Long> {
}
