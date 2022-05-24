package uz.pdp.cutecutapp.mapper.barbershop;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.rating.RatingCreateDto;
import uz.pdp.cutecutapp.dto.rating.RatingDto;
import uz.pdp.cutecutapp.dto.rating.RatingUpdateDto;
import uz.pdp.cutecutapp.entity.barbershop.Rating;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RatingMapper extends BaseMapper<Rating, RatingDto, RatingCreateDto, RatingUpdateDto> {

    Rating fromUpdate(RatingUpdateDto dto, @MappingTarget Rating rating);
}
