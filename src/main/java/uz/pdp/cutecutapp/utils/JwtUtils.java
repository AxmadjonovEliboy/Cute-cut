package uz.pdp.cutecutapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.properties.JwtProperties;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {


    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public  Date getExpireDate(){
        return new Date(jwtProperties.getAccess().getExpire()+System.currentTimeMillis());
    }
    public  Date getExpireDateForRefreshToken(){
        return new Date(jwtProperties.getRefresh().getExpire()
                +System.currentTimeMillis()
                +jwtProperties.getAccess().getExpire());
    }
    public  Algorithm getAlgorithm(){
        return Algorithm.HMAC256(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public  JWTVerifier verifier(){
        return JWT.require(this.getAlgorithm()).build();
    }
}
