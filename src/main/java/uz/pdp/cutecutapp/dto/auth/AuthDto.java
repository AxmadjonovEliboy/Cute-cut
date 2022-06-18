package uz.pdp.cutecutapp.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Role;

@Getter
@Setter

public class AuthDto extends GenericDto {

    public String fullName;

    public String phoneNumber;

    public Long organizationId;

    public Long barberShopId;

    public String picturePath;

    public Role role;

}
