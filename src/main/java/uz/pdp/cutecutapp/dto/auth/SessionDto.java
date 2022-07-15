package uz.pdp.cutecutapp.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SessionDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long refreshTokenExpire;
    private Long issuedAt;
    private Long expiresIn;
    private AuthTokenDto authUser;
}
