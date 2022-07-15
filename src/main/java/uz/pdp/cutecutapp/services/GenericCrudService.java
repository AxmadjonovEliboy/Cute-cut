package uz.pdp.cutecutapp.services;

import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.BaseEntity;

import java.io.Serializable;


/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto,
        C extends BaseCriteria,
        K extends Serializable
        > extends GenericService<D, K, C> {

    DataDto<K> create(CD createDto);

    DataDto<Boolean> delete(K id);

    DataDto<Boolean> update(UD updateDto);

}
