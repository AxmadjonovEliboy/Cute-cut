package uz.pdp.cutecutapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.properties.JwtProperties;
import uz.pdp.cutecutapp.properties.OpenApiProperties;
import uz.pdp.cutecutapp.properties.OtpProperties;
import uz.pdp.cutecutapp.properties.ServerProperties;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.services.organization.OrganizationService;

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

    private final AuthUserRepository repository;
    private final OrganizationService organizationService;
    private final PasswordEncoder encoder;


    public static void main(String[] args) {
        SpringApplication.run(CuteCutAppApplication.class, args);
    }


//    @Bean
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {
            try {
                AuthUser user = repository.save(new AuthUser("Sayfullo", "+998999999999",
                        encoder.encode("+998999999999"), Role.SUPER_ADMIN, Boolean.TRUE));

                DataDto<Long> superOrg = organizationService.create(new OrganizationCreateDto("SuperOrg", user.getId()));

                repository.save(new AuthUser("QambarAli", "+998888888888",
                        encoder.encode("+998888888888"), Boolean.TRUE, Role.ADMIN, superOrg.getData()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        runner.run("s", "b"
        );
    }
}
