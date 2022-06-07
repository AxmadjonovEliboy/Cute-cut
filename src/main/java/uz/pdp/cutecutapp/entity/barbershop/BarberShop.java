package uz.pdp.cutecutapp.entity.barbershop;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class BarberShop extends Auditable {

    private String name;

    private String description;

    private String workingTime;

    private String address;

    private Double latitude;

    private Double longitude;

    private boolean isClosed;

    private Long orgId;

}
