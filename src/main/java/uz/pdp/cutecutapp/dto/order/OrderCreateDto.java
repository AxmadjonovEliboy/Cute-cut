package uz.pdp.cutecutapp.dto.order;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;

import java.util.List;

public class OrderCreateDto implements BaseDto {

    public String orderTime;

    public Long barberShopId;

    public List<ServiceDto> services;
}
