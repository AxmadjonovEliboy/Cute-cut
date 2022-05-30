package uz.pdp.cutecutapp.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> , BaseRepository {
    @Transactional
    @Modifying
    @Query(value = "UPDATE Organization o set o.deleted=true where o.id=:id")
    void isDelete(@Param("id") Long id);

    List<Organization> findAllByDeletedFalse();

    Optional<Organization> findByIdAndDeletedFalse(Long id);
}
