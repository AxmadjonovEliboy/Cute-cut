package uz.pdp.cutecutapp.mapper;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.entity.BaseEntity;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */

public interface BaseMapper<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto> extends GenericMapper{

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD ud);

}