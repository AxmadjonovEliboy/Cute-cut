package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BarberShopRepository extends JpaRepository<BarberShop, Long>, BaseRepository {

    @Transactional
    @Modifying
    @Query("update BarberShop b set b.deleted = true  where b.id = :id")
    void softDelete(@Param("id") Long id);

    Optional<BarberShop> findByIdAndDeletedFalse(Long id);

    List<BarberShop> findByOrgIdAndDeletedFalse(Long id);

    @Query(value = "select * from barber_shop b where (3959 * acos(cos(radians( :latitude)) * cos(radians(b.latitude))" +
            "             * cos(radians(b.longitude) - radians( :longitude)) + sin(radians( :latitude))" +
            "                * sin(radians(b.latitude)))) < :distance/* limit :limit offset :offset*/;", nativeQuery = true)
    List<BarberShop> findByCriteria(@Param("longitude") Double longitude, @Param("latitude") Double latitude,
                                    @Param("distance") int distance/*, @Param("limit") Integer limit, @Param("offset") Integer offset*/);

}
