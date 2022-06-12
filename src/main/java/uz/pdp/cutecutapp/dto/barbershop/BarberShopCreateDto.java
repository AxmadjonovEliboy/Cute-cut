package uz.pdp.cutecutapp.dto.barbershop;


import uz.pdp.cutecutapp.annotations.HaveOrg;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.NotBlank;

public class BarberShopCreateDto implements BaseDto {

    public String description;

    public String workingTime;

    public String address;

    @NotBlank
    public Double latitude;

    @NotBlank
    public Double longitude;

    @NotBlank
    @HaveOrg
    public Long orgId;

}
