package uz.pdp.cutecutapp.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.auth.*;
import uz.pdp.cutecutapp.dto.otp.OtpResponse;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.auth.Device;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.enums.Status;
import uz.pdp.cutecutapp.mapper.auth.AuthUserMapper;
import uz.pdp.cutecutapp.properties.ServerProperties;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.auth.DeviceRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;
import uz.pdp.cutecutapp.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthUserService extends AbstractService<AuthUserRepository,AuthUserMapper> implements UserDetailsService, GenericCrudService<AuthUser, AuthDto, AuthCreateDto, AuthUpdateDto, BaseCriteria, Long> {


    private final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final OtpService otpService;

    private final DeviceRepository deviceRepository;
    private Path root = Paths.get("C:\\uploads");

    public AuthUserService(AuthUserRepository repository, AuthUserMapper mapper, ObjectMapper objectMapper, ServerProperties serverProperties, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, OtpService otpService, DeviceRepository deviceRepository) {
        super(repository, mapper);
        this.objectMapper = objectMapper;
        this.serverProperties = serverProperties;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.otpService = otpService;
        this.deviceRepository = deviceRepository;
    }

    //    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optional = repository.findByPhoneNumberAndDeletedFalse(username);
        if (optional.isPresent()) {
            AuthUser user = optional.get();
            if (user.getStatus().equals(Status.ACTIVE)) {
                return User.builder().username(user.getPhoneNumber()).password(user.getPassword()).authorities(new SimpleGrantedAuthority(user.getRole().name())).build();
            } else throw new RuntimeException("User Not Active");
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


    public DataDto<SessionDto> login(AuthUserPasswordDto dto) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            String url = serverProperties.getServerUrl() + "/api/login";
            HttpPost httppost = new HttpPost(url);
            AuthLoginDto loginDto = new AuthLoginDto(dto.phoneNumber, dto.password);
            byte[] bytes = objectMapper.writeValueAsBytes(loginDto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("data")) {
                Optional<AuthUser> authUser = repository.findByPhoneNumberAndDeletedFalse(dto.phoneNumber);
                deviceRepository.save(new Device(authUser.get().getId(), dto.deviceId, dto.deviceToken));
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new DataDto<>(sessionDto);
            }
            return new DataDto<>(new AppErrorDto("bad Request", " ", HttpStatus.BAD_REQUEST));

        } catch (IOException e) {
            return new DataDto<>(new AppErrorDto("bad request", "", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    private User read(String token) {
        DecodedJWT decodedJWT = jwtUtils.verifier().verify(token);
        String username = decodedJWT.getSubject();
        return loadUser(username);
    }

    private User loadUser(String username) {
        Optional<AuthUser> optional = repository.findByPhoneNumberAndDeletedFalse(username);
        if (optional.isPresent()) {
            AuthUser user = optional.get();
            return new User(user.getPhoneNumber(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
        }
        throw new RuntimeException("user not found");
    }

    public DataDto<SessionDto> refreshToken(String token, HttpServletRequest request) {
        User user = read(token);
        Date expiryForAccessToken = jwtUtils.getExpireDate();
        Date expiryForRefreshToken = jwtUtils.getExpireDateForRefreshToken();
        String accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(expiryForAccessToken).withIssuer(request.getRequestURL().toString()).withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(jwtUtils.getAlgorithm());
        return new DataDto<>(SessionDto.builder().accessToken(accessToken).expiresIn(expiryForAccessToken.getTime()).refreshToken(token).refreshTokenExpire(expiryForRefreshToken.getTime()).issuedAt(System.currentTimeMillis()).build());
    }

    @Override
    public DataDto<Long> create(AuthCreateDto dto) {
        // todo validator qo`shishim kerak

        Role role = Role.ADMIN.checkRole(sessionUser.getRole().toString());

        AuthUser authUser = mapper.fromCreateDto(dto);
        authUser.setRole(role);
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        try {
            return new DataDto<>(repository.save(authUser).getId());
        } catch (Exception e) {
            return new DataDto<>(new AppErrorDto(HttpStatus.IM_USED, "already Taken phone", "auth/user/create"));
        }
    }

    @Override
    public DataDto<Void> delete(Long id) {
        if (Objects.isNull(id)) return new DataDto<>(new AppErrorDto("Bad Request", HttpStatus.BAD_REQUEST));
        Optional<AuthUser> user = repository.getByIdAndNotDeleted(id);
        if (user.isEmpty()) return new DataDto<>(new AppErrorDto("Not Found", HttpStatus.NOT_FOUND));
        String code = UUID.randomUUID().toString();
        repository.softDeleted(id, code);
        return new DataDto<>(null);
    }

    @Override
    public DataDto<Boolean> update(AuthUpdateDto dto) {
        // todo validator qo`shishim kerak
        AuthUser user = mapper.fromUpdateDto(dto);
        repository.save(user);
        return new DataDto<>(true);
    }

    @Override
    public DataDto<List<AuthDto>> getAll() {
        List<AuthUser> users = repository.getAllAndNotIsDeleted();
        return new DataDto<>(mapper.toDto(users));
    }

    @Override
    public DataDto<AuthDto> get(Long id) {
        if (Objects.isNull(id)) return new DataDto<>(new AppErrorDto("Bad Request", HttpStatus.BAD_REQUEST));
        Optional<AuthUser> user = repository.getByIdAndNotDeleted(id);
        if (user.isEmpty()) return new DataDto<>(new AppErrorDto("Not Found", HttpStatus.NOT_FOUND));
        Optional<AuthUser> authUser = repository.getByIdAndNotDeleted(id);
        AuthDto authDto = mapper.toDto(authUser.get());
        return new DataDto<>(authDto);
    }

    @Override
    public DataDto<List<AuthDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {

        return null;
    }

    public DataDto<OtpResponse> loginByPhone(AuthUserPhoneDto loginDto) {

        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(loginDto.phoneNumber);
        if (user.isPresent()) {
            if (user.get().getStatus().equals(Status.ACTIVE)) {
                AuthUser authUser = user.get();
                OtpResponse response = otpService.send(loginDto);
                if (response.success) {
                    repository.setCode(authUser.getId(), response.code);
                    return new DataDto<>(response);
                } else{
                    if (response.message.equals("Unauthorized")){
                        return new DataDto<>(new AppErrorDto("MessageService is not working currently", "/auth/loginPhone", HttpStatus.INTERNAL_SERVER_ERROR));
                    }
                    return new DataDto<>(new AppErrorDto(response.message, "/auth/loginPhone", HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } else return new DataDto<>(new AppErrorDto(HttpStatus.LOCKED, "/auth/loginPhone", "User not Active"));
        } else return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "User not found", "auth/loginPhone"));
    }

    public DataDto<SessionDto> confirmCode(AuthUserCodePhoneDto dto) {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(dto.phoneNumber);
        if (user.isPresent()) {
            AuthUser authUser = user.get();
            if (authUser.getCode().equals(dto.code)) {
                AuthUserPasswordDto passwordDto = new AuthUserPasswordDto(null, dto.phoneNumber, dto.deviceToken, dto.deviceId);
                return this.login(passwordDto);
            } else
                return new DataDto<>(new AppErrorDto(HttpStatus.BAD_REQUEST, "Incorrect Code entered", "/auth/confirmOtp"));
        } else return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "User not found", "/auth/confirmOtp"));
    }

    public DataDto<Long> register(AuthCreateDto dto) {

        Optional<AuthUser> authUser = repository.findByPhoneNumberAndDeletedFalse(dto.getPhoneNumber());
        if (authUser.isPresent())
            return new DataDto<>(new AppErrorDto(HttpStatus.ALREADY_REPORTED, "phone number available", "/auth/register"));


        AuthUser user = mapper.fromCreateDto(dto);

        AuthUser saveUser = repository.save(user);

        return new DataDto<>(saveUser.getId());
    }
}
