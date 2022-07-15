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

    @NotBlank
    public String fullName;

    @NotBlank
    @Pattern(regexp = "[0-9]{9}]")
    public String phoneNumber;

    @NotBlank
    public Long organizationId;

    @NotBlank
    public Long barberShopId;

    public AuthCreateDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public AuthCreateDto(String phoneNumber, Long orgId) {
        this.phoneNumber = phoneNumber;
        this.organizationId = orgId;
    }

}
