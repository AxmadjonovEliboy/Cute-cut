package uz.pdp.cutecutapp.controller.base;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cutecutapp.dto.auth.AuthRefreshToken;
import uz.pdp.cutecutapp.dto.auth.AuthUserDto;
import uz.pdp.cutecutapp.dto.auth.SessionDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AuthUserService authUserService;


    @PostMapping(PATH + "/auth/login")
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody AuthUserDto loginDto) {
        DataDto<SessionDto> login = authUserService.login(loginDto);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }


    @PostMapping(PATH + "/auth/refresh")
    public ResponseEntity<DataDto<SessionDto>> refreshToken(@RequestBody AuthRefreshToken token, HttpServletRequest request) {
        return new ResponseEntity<>(authUserService.refreshToken(token.getToken(), request), HttpStatus.OK);
    }

}
