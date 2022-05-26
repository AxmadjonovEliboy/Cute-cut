package uz.pdp.cutecutapp.dto.faq;

import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.NotBlank;

public class FaqCreateDto implements BaseDto {

    @NotBlank
    public String title;
    @NotBlank
    public String description;

}
