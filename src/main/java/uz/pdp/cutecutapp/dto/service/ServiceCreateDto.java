package uz.pdp.cutecutapp.dto.service;

import uz.pdp.cutecutapp.annotations.HaveBarberShop;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ServiceCreateDto implements BaseDto {

    @NotBlank(message = "Service type required")
    @Size(min = 3)
    public String type;

    @NotBlank(message = "Service price required")
    public Double price;

    @NotBlank(message = "Service time required")
    public Integer time;

    @HaveBarberShop
    @NotBlank(message = "Barbershop id required")
    public Long barberShopId;
}
