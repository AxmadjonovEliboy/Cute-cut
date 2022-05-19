package uz.pdp.cutecutapp.dto.auth;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OtpDto {

    public String recipients;
    public String originator;
    public String body;

}
