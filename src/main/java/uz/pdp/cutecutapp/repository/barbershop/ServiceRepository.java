package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.barbershop.Service;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long>, BaseRepository {
    boolean existsByType(String type);

    @Transactional
    @Modifying
    @Query("update Service r set r.deleted = true  where r.id = :id")
    void softDelete(@Param("id") Long id);
    Optional<Service> findByIdAndDeletedFalse(Long id);

    List<Service> findAllByBarberShopIdAndDeletedFalse(Long barberShopId);

}
