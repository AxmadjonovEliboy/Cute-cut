package uz.pdp.cutecutapp.dto.rating;

import uz.pdp.cutecutapp.annotations.HaveBarberShop;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RatingCreateDto implements BaseDto {

    public String feedback;

    @Positive
    @Min(value = 0)
    @Max(value = 5)
    public Integer rate;

    @NotBlank
    @HaveBarberShop
    public Long barberShopId;

}
