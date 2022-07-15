package uz.pdp.cutecutapp.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto extends GenericDto {

    @JsonProperty("order_time")
    public String orderTime;

    public boolean isAccepted;

    public boolean isCancelled;

    public boolean isDone;

    @JsonProperty("created_at")
    public LocalDateTime createdAt;
    
    public BarberShopDto barbershop;

    public List<ServiceDto> services;

    @JsonProperty("client_id")
    public Long clientId;

}
