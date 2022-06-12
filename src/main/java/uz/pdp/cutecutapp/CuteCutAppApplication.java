package uz.pdp.cutecutapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import uz.pdp.cutecutapp.dto.auth.AuthCreateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.properties.JwtProperties;
import uz.pdp.cutecutapp.properties.OpenApiProperties;
import uz.pdp.cutecutapp.properties.OtpProperties;
import uz.pdp.cutecutapp.properties.ServerProperties;
import uz.pdp.cutecutapp.services.auth.AuthUserService;
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

    private final AuthUserService service;
    private final OrganizationService organizationService;


    public static void main(String[] args) {
        SpringApplication.run(CuteCutAppApplication.class, args);
    }


    @Bean
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {
            DataDto<Long> id = service.create(new AuthCreateDto("+998999999999", "+998999999999", Role.SUPER_ADMIN.name()));
            DataDto<Long> superOrg = organizationService.create(new OrganizationCreateDto("SuperOrg", id.getData()));
            service.create(new AuthCreateDto("+998888888888", "+998888888888", Role.ADMIN.name(), superOrg.getData()));

        };
        runner.run("s", "b"
        );
    }
}
