package uz.pdp.cutecutapp.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
