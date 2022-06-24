package uz.pdp.cutecutapp.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {

    Optional<AuthUser> findByPhoneNumberAndDeletedFalse(String username);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update auth_user  set deleted=true , username = (username || ?2 )  where id = ?1 ", nativeQuery = true)
//    void delete(Long id, String token);

    @Query(value = "select a from AuthUser a where a.deleted = false  and a.id = :id")
    Optional<AuthUser> getByIdAndNotDeleted(@Param("id") Long id);

    List<AuthUser> findAllByBarberShopId(Long barberShopId);

    @Transactional
    @Modifying
    @Query(value = "update auth_user   set deleted = true , phone_number = (phone_number || :token ) where id = :id", nativeQuery = true)
    void softDeleted(@Param("id") Long id, @Param("token") String toke);


    List<AuthUser> findAllByDeletedFalse();

    List<AuthUser> findAllByOrganizationIdAndDeletedFalse(Long id);

    @Transactional
    @Modifying
    @Query(value = "update auth_user  set code = :code  where id = :id", nativeQuery = true)
    void setCode(@Param("id") Long id, @Param("code") Integer code);

    @Transactional
    @Modifying
    @Query(value = "select a from AuthUser a where a.organizationId=:id and a.deleted=false and a.role='ADMIN'")
    Optional<AuthUser> findByAndOrganizationId(@Param("id") Long id);

    Optional<AuthUser> findByPhoneNumberAndRole(String phoneNumber, Role role);

    Optional<AuthUser> findByPhoneNumberAndRoleIn(String phoneNumber, Collection<Role> role);


}
