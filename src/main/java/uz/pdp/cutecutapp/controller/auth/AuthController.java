package uz.pdp.cutecutapp.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.auth.*;
import uz.pdp.cutecutapp.dto.otp.OtpResponse;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AuthController extends AbstractController<AuthUserService> {
    protected AuthController(AuthUserService authUserService, AuthUserService authUserService1) {
        super(authUserService);
    }


    @PostMapping(PATH + "/auth/loginByPhone")
    public ResponseEntity<DataDto<OtpResponse>> loginByPhone(@RequestBody AuthUserPhoneDto loginDto) {
        return new ResponseEntity<>(service.loginByPhone(loginDto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/auth/confirmLoginCode")
    public ResponseEntity<DataDto<SessionDto>> confirmLoginSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(service.confirmLoginCode(dto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/auth/confirmRegisterCode")
    public ResponseEntity<DataDto<SessionDto>> confirmRegisterSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(service.confirmRegisterCode(dto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/auth/loginByPassword")
    public ResponseEntity<DataDto<SessionDto>> loginByPassword(@RequestBody AuthUserPasswordDto loginDto) {
        return new ResponseEntity<>(service.login(loginDto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/auth/refresh")
    public ResponseEntity<DataDto<SessionDto>> refreshToken(@RequestBody AuthRefreshToken token, HttpServletRequest request) {
        return new ResponseEntity<>(service.refreshToken(token.getToken(), request), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(PATH + "/auth/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/auth/register")
    public ResponseEntity<DataDto<Boolean>> register(@RequestBody AuthUserPhoneDto dto) {
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
    }

    @PostMapping

    @PutMapping(PATH + "/auth/update")
    public ResponseEntity<DataDto<Boolean>> update(@RequestBody AuthUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/auth/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/auth/{id}")
    public ResponseEntity<DataDto<AuthDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/auth")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }


}
