package uz.pdp.cutecutapp.services;

import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


/**
 * @param <D> DTO
 * @param <K> Id Long
 */
public interface GenericService<
        D extends BaseDto,
        K extends Serializable,
        C extends BaseCriteria
        > extends BaseService {


    DataDto<List<D>> getAll();

    DataDto<D> get(K id);

    DataDto<List<D>> getWithCriteria(C criteria);
}
