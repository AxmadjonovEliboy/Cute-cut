package uz.pdp.cutecutapp.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> , BaseRepository {
    @Transactional
    @Modifying
    @Query(value = "update organization  set deleted = true where id = :id",nativeQuery = true)
    void isDelete(@Param("id") Long id);

    List<Organization> findAllByAndDeletedFalse();


    Optional<Organization> findByIdAndDeletedFalse(Long id);
}
