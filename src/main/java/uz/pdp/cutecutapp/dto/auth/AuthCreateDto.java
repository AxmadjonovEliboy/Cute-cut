package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.cutecutapp.annotations.HaveOrg;
import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCreateDto implements BaseDto {

    @NotBlank
    @Pattern(regexp = "[+998][0-9]{9}]")
    private String phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    private Long organizationId;

    private Long barbershopId;

}
