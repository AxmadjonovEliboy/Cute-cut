package uz.pdp.cutecutapp.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private Access access;

    private Refresh refresh;

    @Data
    public static class Access {

        private Long expire;
    }

    @Data
    public static class Refresh {

        private Long expire;
    }
}
