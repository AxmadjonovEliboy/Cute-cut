package uz.pdp.cutecutapp.validator.barberShop;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.annotations.HaveBarberShop;
import uz.pdp.cutecutapp.services.barbershop.BarberShopService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class BarberShopValidator implements ConstraintValidator<HaveBarberShop, Long> {

    private final BarberShopService service;

    public BarberShopValidator(BarberShopService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(id).isSuccess();
    }
}
