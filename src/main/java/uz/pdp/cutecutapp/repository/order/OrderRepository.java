package uz.pdp.cutecutapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}