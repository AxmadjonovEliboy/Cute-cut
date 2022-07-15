package uz.pdp.cutecutapp.criteria;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BarberShopCriteria extends GenericCriteria implements BaseCriteria {

    private Double longitude;

    private Double latitude;

    private int distance;
}
