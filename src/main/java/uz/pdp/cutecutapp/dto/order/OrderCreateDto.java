package uz.pdp.cutecutapp.dto.order;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreateDto implements BaseDto {

    public LocalDateTime orderTime;

    public Long clientId;

    public Long barberId;

    public List<ServiceDto> services;
}
