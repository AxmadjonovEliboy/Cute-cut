package uz.pdp.cutecutapp.dto.auth;


import lombok.AllArgsConstructor;
import uz.pdp.cutecutapp.enums.Language;
import uz.pdp.cutecutapp.enums.Status;

@AllArgsConstructor
public class AuthTokenDto {

    public String fullName;

    public String phoneNumber;

    public String language;

    public String role;

    public String status;

    public Long organizationId;

    public Long barberShopId;

}
