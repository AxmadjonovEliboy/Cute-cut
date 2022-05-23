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
    @Pattern(regexp = "[+998][0-9]{9}")
    private String phoneNumber;

    private String password;

//    private String role;

    private Long organizationId;

    private Long barbershopId;

}
