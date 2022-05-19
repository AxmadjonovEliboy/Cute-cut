package uz.pdp.cutecutapp.validator.auth;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.auth.AuthCreateDto;
import uz.pdp.cutecutapp.dto.auth.AuthUpdateDto;
import uz.pdp.cutecutapp.validator.BaseValidator;

/**
 * @author Axmadjonov Eliboy on Thu 15:25. 19/05/22
 * @project cute-cut-app
 */
@Component
public class AuthCreateValidator implements BaseValidator {

    public void validOnCreate(AuthCreateDto dto) {

    }

    public void validOnUpdate(AuthUpdateDto dto) {

    }

    public void validKey(Long id) {

    }

}
