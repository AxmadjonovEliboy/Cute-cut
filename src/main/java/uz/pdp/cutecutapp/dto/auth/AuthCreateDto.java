package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCreateDto implements BaseDto {

    private String firstName;

    private String lastName;

    @NotBlank
    @Pattern(regexp = "[0-9]{9}]")
    private String phoneNumber;

    @NotBlank
    private String password;

    private String role;

    private Long organizationId;

    private Long barbershopId;

    public AuthCreateDto(String phoneNumber, String password, String role, Long organizationId, Long barbershopId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.organizationId = organizationId;
        this.barbershopId = barbershopId;
    }

    public AuthCreateDto(String phoneNumber, String role) {
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
