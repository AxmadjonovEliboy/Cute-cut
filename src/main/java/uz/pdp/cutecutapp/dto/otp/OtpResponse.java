package uz.pdp.cutecutapp.dto.otp;

public class OtpResponse {

    public boolean success;

    public Integer code;
    public String message;

    public String phoneNumber;

    public OtpResponse(boolean success) {
        this.success = success;
    }

    public OtpResponse(Integer code, String phoneNumber) {
        this.code = code;
        this.success = true;
        this.phoneNumber = phoneNumber;
    }

    public OtpResponse(boolean success, Integer code) {
        this(success);
        this.code = code;
    }

    public OtpResponse(boolean success, Integer code, String message) {
        this(success, code);
        this.message = message;
    }
}
