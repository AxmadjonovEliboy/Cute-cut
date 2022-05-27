package uz.pdp.cutecutapp.config.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.config.encoder.PasswordConfig;
import uz.pdp.cutecutapp.dto.organization.OrganizationDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.enums.Status;
import uz.pdp.cutecutapp.exception.OrganizationNotActiveException;
import uz.pdp.cutecutapp.exception.UserNotActiveException;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.services.organization.OrganizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthUserRepository userRepository;
    private final PasswordConfig passwordConfig;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String phoneNumber = authentication.getPrincipal().toString();
        Object credentials = authentication.getCredentials();
        List<GrantedAuthority> authorities = new ArrayList<>();

        Optional<AuthUser> user = userRepository.findByPhoneNumberAndDeletedFalse(phoneNumber);

        if (user.isEmpty()) throw new RuntimeException("Bad Credentials");

        AuthUser authUser = user.get();
        if (!authUser.getStatus().equals(Status.ACTIVE)) {
            throw new UserNotActiveException();
        }
//        DataDto<OrganizationDto> dataDto = organizationService.get(authUser.getOrganizationId());
//        if (!dataDto.isSuccess()) {
//            OrganizationDto organizationDto = dataDto.getData();
//            if (!organizationDto.status.equals(Status.ACTIVE)) {
//                throw new OrganizationNotActiveException();
//            }
//        }

        authorities.add(new SimpleGrantedAuthority(authUser.getRole().name()));

        if (Objects.isNull(credentials)) {
            UserDetails userDetails = new User(phoneNumber, "11", authorities);
            return new UsernamePasswordAuthenticationToken(userDetails, "11", authorities);
        } else if (passwordConfig.passwordEncoder().matches(credentials.toString(),authUser.getPassword())/*authUser.getPassword().equals(credentials.toString())*/) {
            UserDetails userDetails = new User(phoneNumber, credentials.toString(), authorities);
            return new UsernamePasswordAuthenticationToken(userDetails, credentials.toString(), authorities);
        } else
            throw new RuntimeException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
