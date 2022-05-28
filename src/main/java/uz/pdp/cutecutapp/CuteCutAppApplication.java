package uz.pdp.cutecutapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import uz.pdp.cutecutapp.dto.auth.AuthCreateDto;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.properties.JwtProperties;
import uz.pdp.cutecutapp.properties.OpenApiProperties;
import uz.pdp.cutecutapp.properties.OtpProperties;
import uz.pdp.cutecutapp.properties.ServerProperties;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

@EnableConfigurationProperties(value = {
        OpenApiProperties.class,
        ServerProperties.class,
        JwtProperties.class,
        OtpProperties.class
})
@OpenAPIDefinition
@SpringBootApplication
@RequiredArgsConstructor
public class CuteCutAppApplication {

    private final AuthUserService service;


    public static void main(String[] args) {
        SpringApplication.run(CuteCutAppApplication.class, args);
    }



    @Bean
    @Profile("dev")
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {
            service.create(new AuthCreateDto("+998990473164", "123", Role.ADMIN.name(), -1L, -1L));
        };
        runner.run("s", "b"
        );
    }
}
