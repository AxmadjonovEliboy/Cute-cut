package uz.pdp.cutecutapp.dto.faq;

import uz.pdp.cutecutapp.dto.GenericDto;

import javax.validation.constraints.NotBlank;

public class FaqUpdateDto extends GenericDto {
    @NotBlank
    public String title;
    @NotBlank
    public String description;

}
