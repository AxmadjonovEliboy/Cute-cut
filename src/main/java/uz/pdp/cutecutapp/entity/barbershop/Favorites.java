package uz.pdp.cutecutapp.entity.barbershop;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Favorites extends Auditable {

    private Long clientId;

    private Long barberShopId;
}
