package uz.pdp.cutecutapp.annotations;

import uz.pdp.cutecutapp.validator.auth.AuthUserValidator;
import uz.pdp.cutecutapp.validator.organization.OrganizationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AuthUserValidator.class)
public @interface HaveUser {
    String message() default "User is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
