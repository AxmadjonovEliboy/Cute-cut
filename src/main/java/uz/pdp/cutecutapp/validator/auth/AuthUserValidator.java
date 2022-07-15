package uz.pdp.cutecutapp.validator.auth;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.annotations.HaveUser;
import uz.pdp.cutecutapp.services.auth.AuthUserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AuthUserValidator implements ConstraintValidator<HaveUser, Long> {

    private final AuthUserService userService;

    public AuthUserValidator(AuthUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return userService.get(id).isSuccess();
    }
}
