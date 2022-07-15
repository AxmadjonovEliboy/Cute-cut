package uz.pdp.cutecutapp.dto.rating;

import org.checkerframework.checker.index.qual.Positive;
import uz.pdp.cutecutapp.dto.GenericDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RatingUpdateDto extends GenericDto {

    public String feedback;

    @Positive
    @Min(value = 0)
    @Max(value = 5)
    public Integer rate;

}
