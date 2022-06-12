package uz.pdp.cutecutapp.entity.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Auditable {

    private String orderTime;

    private boolean isAccepted;

    private boolean isCancelled;

    private boolean isDone;

    private Long barbershopId;

    private Long clientId;
}
