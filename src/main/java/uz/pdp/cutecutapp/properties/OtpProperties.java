package uz.pdp.cutecutapp.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "otp")
public class OtpProperties {

    private String accessKey;

    private String originator;

    private String api;

    private String body;

    private Integer from;

    private Integer bound;

    private Integer expiration;
}
