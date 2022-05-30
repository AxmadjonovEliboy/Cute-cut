package uz.pdp.cutecutapp.dto.service;

import uz.pdp.cutecutapp.annotations.HaveBarberShop;
import uz.pdp.cutecutapp.dto.GenericDto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ServiceUpdateDto extends GenericDto {

    @NotBlank(message = "Service type required")
    @Size(min = 3)
    public String type;
    @NotBlank(message = "Service price required")
    @Column(precision = 3, scale = 2)
    public Double price;

    public boolean isDeleted = false;
}
