package uz.pdp.cutecutapp.dto.auth;

import javax.validation.constraints.Pattern;

public class AuthUserCodePhoneDto {


    @Pattern(regexp = "[0-9]{9}]")
    public String phoneNumber;

    public Integer code;

    public String deviceId;

    public String deviceToken;
}
