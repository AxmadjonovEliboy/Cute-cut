package uz.pdp.cutecutapp.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.cutecutapp.entity.auth.PhoneCode;

import java.util.Optional;

/**
 * @author Axmadjonov Eliboy on Mon 15:26. 23/05/22
 * @project cute-cut-app
 */
@Repository
public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Long> {
    @Query(value = "select p.* from phone_code p where p.phone_number = :phoneNumber order by p.id desc limit 1", nativeQuery = true)
    Optional<PhoneCode> findByPhoneNumberAndDeletedFalse(@Param("phoneNumber") String phoneNumber);
}
