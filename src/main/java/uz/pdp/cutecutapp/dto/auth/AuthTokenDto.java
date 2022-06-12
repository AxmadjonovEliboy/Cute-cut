package uz.pdp.cutecutapp.dto.auth;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthTokenDto {

    public Long id;

    public String fullName;

    public String phoneNumber;

    public String language;

    public String role;

    public String status;

    public Long organizationId;

    public Long barberShopId;

}
