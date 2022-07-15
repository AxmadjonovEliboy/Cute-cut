package uz.pdp.cutecutapp.entity.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Auditable {

    private LocalDateTime orderTime;

    private boolean isAccepted;

    private boolean isCancelled;

    private boolean isDone;

    private Long barberId;

    private Long barberShopId;

    private Long clientId;
}
