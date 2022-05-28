package uz.pdp.cutecutapp.validator.order;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.annotations.HaveOrder;
import uz.pdp.cutecutapp.services.order.OrderService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class OrderValidator implements ConstraintValidator<HaveOrder,Long> {

    private final OrderService orderService;

    public OrderValidator(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return orderService.get(id).isSuccess();
    }
}
