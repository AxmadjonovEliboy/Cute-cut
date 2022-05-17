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

    private Integer reminderTime;

    private boolean isAccepted;

    private boolean isCancelled;

    private boolean isReminder;

    private Long barbershopId;

    private Long clientId;
}
