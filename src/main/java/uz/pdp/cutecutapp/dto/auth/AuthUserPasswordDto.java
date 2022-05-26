package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserPasswordDto {

    @Pattern(regexp = "[0-9]{9}]")
    public String password;

    public String phoneNumber;

    public String deviceToken;

    public String deviceId;

}
