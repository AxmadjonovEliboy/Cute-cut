package uz.pdp.cutecutapp.dto.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import uz.pdp.cutecutapp.dto.GenericDto;

public class ServiceDto extends GenericDto {

    public String type;

    public Double price;

    public Integer time;

    @JsonProperty("barber_shop_id")
    public Long barberShopId;
}
