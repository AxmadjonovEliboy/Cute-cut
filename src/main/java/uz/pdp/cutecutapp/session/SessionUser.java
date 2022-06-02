package uz.pdp.cutecutapp.session;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.enums.Language;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.enums.Status;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionUser {

    private final AuthUserRepository repository;


    public String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getId() {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(this.getUsername());
        return user.get().getId();
    }

    public Long getOrgId() {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(this.getUsername());
        return user.get().getOrganizationId();
    }

    public Role getRole() {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(this.getUsername());
        return user.get().getRole();
    }

    public Language getLanguage() {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(this.getUsername());
        return user.get().getLanguage();
    }

    public Status getStatus() {
        Optional<AuthUser> user = repository.findByPhoneNumberAndDeletedFalse(this.getUsername());
        return user.get().getStatus();
    }

}
