package uz.pdp.cutecutapp.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.auth.AuthUser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByPhoneNumberAndDeletedFalse(String username);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update auth_user  set deleted=true , username = (username || ?2 )  where id = ?1 ", nativeQuery = true)
//    void delete(Long id, String token);

    @Query(value = "select a from AuthUser a where a.deleted = false  and a.id = :id")
    Optional<AuthUser> getByIdAndNotDeleted(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update auth_user   set deleted = true , phone_number = (phone_number || :token ) where id = :id", nativeQuery = true)
    void softDeleted(@Param("id") Long id, @Param("token") String toke);

    @Query(value = "select a from  AuthUser a where a.deleted = false ")
    List<AuthUser> getAllAndNotIsDeleted();

    @Transactional
    @Modifying
    @Query(value = "update auth_user  set code = :code  where id = :id", nativeQuery = true)
    void setCode(@Param("id") Long id, @Param("code") Integer code);

}
