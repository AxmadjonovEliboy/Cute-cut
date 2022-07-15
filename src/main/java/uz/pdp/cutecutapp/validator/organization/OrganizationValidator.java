package uz.pdp.cutecutapp.validator.organization;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.annotations.HaveOrg;
import uz.pdp.cutecutapp.services.organization.OrganizationService;
import uz.pdp.cutecutapp.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class OrganizationValidator implements ConstraintValidator<HaveOrg, Long>, BaseValidator {

    private final OrganizationService service;

    public OrganizationValidator(OrganizationService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(id).isSuccess();
    }
}
