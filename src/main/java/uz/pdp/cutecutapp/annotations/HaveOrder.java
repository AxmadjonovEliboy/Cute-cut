package uz.pdp.cutecutapp.annotations;

import uz.pdp.cutecutapp.validator.order.OrderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderValidator.class)
public @interface HaveOrder {

    String message() default "User is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
