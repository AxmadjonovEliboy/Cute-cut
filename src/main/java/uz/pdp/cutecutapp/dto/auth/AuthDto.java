package uz.pdp.cutecutapp.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Role;

@Getter
@Setter
public class AuthDto extends GenericDto {

    private String fullName;

    private String username;

    private String phoneNumber;

    private Long organizationId;

    private String picturePath;

    private Role role;

}
