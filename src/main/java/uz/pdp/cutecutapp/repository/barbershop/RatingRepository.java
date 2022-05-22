package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.barbershop.Rating;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Transactional
    @Modifying
    @Query("update Rating r set r.deleted = true  where r.id = :id")
    void softDelete(@Param("id") Long id);

    List<Rating> findAllByDeletedFalse();

    Optional<Rating> findByIdAndDeletedFalse(Long id);

    List<Rating> findByBarberShopIdAndDeletedFalse(Long barberShopId);
}
