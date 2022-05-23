package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserPasswordDto {

    public String password;

    public String phoneNumber;

    public String deviceToken;

    public String deviceId;

}
