package uz.pdp.cutecutapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.order.OrderService;

public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

    void deleteAllByOrderId(Long orderId);
}
