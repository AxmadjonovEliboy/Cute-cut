package uz.pdp.cutecutapp.annotations;

import uz.pdp.cutecutapp.validator.barberShop.BarberShopValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BarberShopValidator.class)
public @interface HaveBarberShop {

    String message() default "BarberShop is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
