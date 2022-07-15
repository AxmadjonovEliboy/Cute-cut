package uz.pdp.cutecutapp.validator;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.GenericDto;

/**
 * @param <CD>Create Dto
 * @param <UD>Update Dto
 */


public interface GenericValidator<
        CD extends BaseDto,
        UD extends GenericDto> extends BaseValidator {

    boolean validForCreate(CD createDto);

    boolean validForUpdate(UD updateDto);
}
