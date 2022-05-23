package uz.pdp.cutecutapp.entity.barbershop;

import lombok.*;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating extends Auditable {

    private String feedback;

    private Integer rate;

    private Long barberShopId;

    private Long clientId;

}
