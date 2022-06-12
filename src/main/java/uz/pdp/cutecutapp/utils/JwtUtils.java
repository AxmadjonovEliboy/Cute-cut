package uz.pdp.cutecutapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.auth.AuthTokenDto;
import uz.pdp.cutecutapp.dto.auth.SessionDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.properties.JwtProperties;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {


    private final JwtProperties jwtProperties;
    private final AuthUserService authUserService;

    public JwtUtils(JwtProperties jwtProperties, @Lazy AuthUserService authUserService) {
        this.jwtProperties = jwtProperties;
        this.authUserService = authUserService;
    }

    public Date getExpireDate() {
        return new Date(jwtProperties.getAccess().getExpire() + System.currentTimeMillis());
    }

    public Date getExpireDateForRefreshToken() {
        return new Date(jwtProperties.getRefresh().getExpire()
                + System.currentTimeMillis()
                + jwtProperties.getAccess().getExpire());
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public SessionDto generateSessionDto(User user) {
        Date expiryForAccessToken = this.getExpireDate();
        Date expiryForRefreshToken = this.getExpireDateForRefreshToken();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForAccessToken)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(this.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForRefreshToken)
                .sign(this.getAlgorithm());

        AuthUser authUser = authUserService.loadAuthUserByUsername(user.getUsername());
        AuthTokenDto authTokenDto = new AuthTokenDto(authUser.getId(), authUser.getFullName(), authUser.getPhoneNumber(), authUser.getLanguage().getName()
                , authUser.getRole().name(), authUser.getStatus().name(), authUser.getOrganizationId(), authUser.getPictureId());

        return SessionDto.builder()
                .accessToken(accessToken)
                .expiresIn(expiryForAccessToken.getTime())
                .refreshToken(refreshToken)
                .refreshTokenExpire(expiryForRefreshToken.getTime())
                .issuedAt(System.currentTimeMillis())
                .authUser(authTokenDto)
                .build();
    }

    public JWTVerifier verifier() {
        return JWT.require(this.getAlgorithm()).build();
    }
}
