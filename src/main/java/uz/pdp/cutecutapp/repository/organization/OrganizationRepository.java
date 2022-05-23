package uz.pdp.cutecutapp.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> , BaseRepository {
}
