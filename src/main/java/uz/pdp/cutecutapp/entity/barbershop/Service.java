package uz.pdp.cutecutapp.entity.barbershop;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Service extends Auditable {

    private String type;

    private Double price;

    private Integer time;

    private Long barberShopId;
}
