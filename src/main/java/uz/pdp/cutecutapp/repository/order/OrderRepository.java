package uz.pdp.cutecutapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, BaseRepository {

    @Transactional
    @Modifying
    @Query("update Order o set o.deleted = true  where o.id = :id")
    void softDelete(@Param("id") Long id);

    Optional<Order> findByIdAndDeletedFalse(Long id);

    List<Order> findAllByDeletedFalse();

    @Query(value = "select public.get_orders_by_user_id(:clientId)", nativeQuery = true)
    String findPassedByClientIdAndDeletedFalse(@Param("clientId") Long clientId);

    @Query(value = "select public.get_upcoming_orders_by_user_id(:clientId)", nativeQuery = true)
    String findUpcomingByClientId(@Param("clientId") Long clientId);
}
