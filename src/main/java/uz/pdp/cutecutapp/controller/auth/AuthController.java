package uz.pdp.cutecutapp.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.auth.*;
import uz.pdp.cutecutapp.dto.otp.OtpResponse;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.BusyTime;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import java.util.List;

@RestController
@RequestMapping("/auth")
//@EnableGlobalMethodSecurity
public class AuthController extends AbstractController<AuthUserService> {
    protected AuthController(AuthUserService authUserService) {
        super(authUserService);
    }


    @PostMapping(PATH + "/loginByPhone")
    public ResponseEntity<DataDto<OtpResponse>> loginByPhone(@RequestBody AuthUserPhoneDto loginDto) {
        return new ResponseEntity<>(service.loginByPhone(loginDto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/confirmUserCode")
    public ResponseEntity<DataDto<SessionDto>> confirmUserSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(service.confirmUserCode(dto, Role.CLIENT), HttpStatus.OK);
    }

    @PostMapping(PATH + "/confirmAdminCode")
    public ResponseEntity<DataDto<SessionDto>> confirmAdminSms(@RequestBody AuthUserCodePhoneDto dto) {
        return new ResponseEntity<>(service.confirmUserCode(dto, Role.ADMIN), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PostMapping(PATH + "/register")
    public ResponseEntity<DataDto<Boolean>> register(@RequestBody AuthUserPhoneDto dto) {
        return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
    }


    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@RequestBody AuthUpdateDto dto) {
        dto.phoneNumber = String.format("+998%s", dto.phoneNumber);
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('CLIENT')")
    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<AuthDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getAll")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(PATH + "/block/{id}")
    public ResponseEntity<DataDto<Boolean>> block(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.block(id), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(PATH + "/unblock{id}")
    public ResponseEntity<DataDto<Boolean>> unblockUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.unblock(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getBusyTimesByBarberId/{id}")
    public ResponseEntity<DataDto<List<BusyTime>>> getBusyTimes(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getBusyTimesOfBarber(id), HttpStatus.OK);
    }

    @PostMapping(PATH + "/changeBusyTime")
    public ResponseEntity<DataDto<BusyTime>> changeBusyTime(@RequestBody BusyTime busyTime) {
        return new ResponseEntity<>(service.changeBusyTime(busyTime), HttpStatus.OK);
    }

}
