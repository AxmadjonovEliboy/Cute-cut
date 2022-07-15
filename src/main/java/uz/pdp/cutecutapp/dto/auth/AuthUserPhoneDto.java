package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserPhoneDto {

    @NotBlank
    @Pattern(regexp = "[0-9]{9}]")
    public String phoneNumber;

}
