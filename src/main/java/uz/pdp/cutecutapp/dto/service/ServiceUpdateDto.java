package uz.pdp.cutecutapp.dto.service;

import uz.pdp.cutecutapp.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ServiceUpdateDto extends GenericDto {

    @NotBlank(message = "Service type required")
    @Size(min = 3)
    public String type;

    @NotBlank(message = "Service price required")
    public Double price;

    @NotBlank(message = "Service time required")
    public Integer time;

    public boolean isDeleted = false;
}
