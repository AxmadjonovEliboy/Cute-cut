package uz.pdp.cutecutapp.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Language;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthUpdateDto extends GenericDto {

    public String firstName;

    public String lastName;

    @NotBlank
    public String phoneNumber;


    public Language language;
}
