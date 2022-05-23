package uz.pdp.cutecutapp.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Role;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class AuthUpdateDto extends GenericDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;


    private Role role;
}
