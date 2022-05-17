package uz.pdp.cutecutapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.order.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
