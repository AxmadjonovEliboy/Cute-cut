package uz.pdp.cutecutapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.order.Notification;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> , BaseRepository {

    @Transactional
    @Modifying
    @Query("update Notification n set n.deleted = true  where n.id = :id")
    void softDelete(@Param("id") Long id);

    Optional<Notification> findByIdAndDeletedFalse(Long id);
    List<Notification> findAllByDeletedFalse();

    List<Notification> getNotificationByReceiverId(Long id);
}
