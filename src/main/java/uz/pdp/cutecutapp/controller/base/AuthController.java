package uz.pdp.cutecutapp.controller.base;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cutecutapp.dto.auth.AuthRefreshToken;
import uz.pdp.cutecutapp.dto.auth.AuthUserPhoneDto;
import uz.pdp.cutecutapp.dto.auth.SessionDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.dto.auth.*;
import uz.pdp.cutecutapp.dto.otp.OtpResponse;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AuthUserService authUserService;


    @PostMapping(PATH + "/auth/loginByPhone")
    public ResponseEntity<DataDto<OtpResponse>> loginByPhone(@RequestBody AuthUserPhoneDto loginDto) {
        return new ResponseEntity<>(authUserService.loginByPhone(loginDto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/auth/confirmOtp")
    public ResponseEntity<DataDto<SessionDto>> confirmSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(authUserService.confirmCode(dto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/auth/loginByPassword")
    public ResponseEntity<DataDto<SessionDto>> loginByPassword(@RequestBody AuthUserPasswordDto loginDto) {
        return new ResponseEntity<>(authUserService.login(loginDto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/auth/refresh")
    public ResponseEntity<DataDto<SessionDto>> refreshToken(@RequestBody AuthRefreshToken token, HttpServletRequest request) {
        return new ResponseEntity<>(authUserService.refreshToken(token.getToken(), request), HttpStatus.OK);
    }

    @PreAuthorize(value = "")
    @PostMapping(PATH + "/auth/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthCreateDto dto) {
        return new ResponseEntity<>(authUserService.create(dto), HttpStatus.OK);
    }

    @PutMapping(PATH + "/auth/update")
    public ResponseEntity<DataDto<Boolean>> update(@RequestBody AuthUpdateDto dto) {
        return new ResponseEntity<>(authUserService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/auth/{id}")
    public ResponseEntity<DataDto<Void>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(authUserService.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/auth/{id}")
    public ResponseEntity<DataDto<AuthDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(authUserService.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/auth")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return new ResponseEntity<>(authUserService.getAll(), HttpStatus.OK);
    }

}
