package ECOBackend.repo;

import ECOBackend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo  extends JpaRepository<Organization, Long> {
}
