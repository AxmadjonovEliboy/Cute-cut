package uz.pdp.cutecutapp.entity.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;
import uz.pdp.cutecutapp.enums.NotificationMessage;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Notification extends Auditable {

    private NotificationMessage message;

    private Long receiverId;

    private Long senderId;

    private Long orderId;
}
