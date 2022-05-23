package uz.pdp.cutecutapp.controller.base;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//@RequestMapping(value = PATH + "/auth")

public class AuthController extends AbstractController {
    private final AuthUserService authUserService;


    @PostMapping(PATH + "/loginByPhone")
    public ResponseEntity<DataDto<OtpResponse>> loginByPhone(@RequestBody AuthUserPhoneDto loginDto) {
        return new ResponseEntity<>(authUserService.loginByPhone(loginDto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/confirmOtp")
    public ResponseEntity<DataDto<SessionDto>> confirmSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(authUserService.confirmCode(dto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/loginByPassword")
    public ResponseEntity<DataDto<SessionDto>> loginByPassword(@RequestBody AuthUserPasswordDto loginDto) {
        return new ResponseEntity<>(authUserService.login(loginDto), HttpStatus.OK);
    }


    @PostMapping(PATH + "/refresh")
    public ResponseEntity<DataDto<SessionDto>> refreshToken(@RequestBody AuthRefreshToken token, HttpServletRequest request) {
        return new ResponseEntity<>(authUserService.refreshToken(token.getToken(), request), HttpStatus.OK);
    }

    @PreAuthorize(value = "")
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthCreateDto dto) {
        return new ResponseEntity<>(authUserService.create(dto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/register")
    public ResponseEntity<DataDto<Long>> register(@RequestBody AuthCreateDto dto) {
        return new ResponseEntity<>(authUserService.register(dto), HttpStatus.OK);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@RequestBody AuthUpdateDto dto) {
        return new ResponseEntity<>(authUserService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Void>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(authUserService.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<AuthDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(authUserService.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return new ResponseEntity<>(authUserService.getAll(), HttpStatus.OK);
    }

}
