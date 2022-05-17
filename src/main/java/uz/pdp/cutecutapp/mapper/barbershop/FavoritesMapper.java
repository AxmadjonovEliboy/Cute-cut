package uz.pdp.cutecutapp.mapper.barbershop;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesDto;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface FavoritesMapper extends BaseMapper<Favorites, FavoritesDto, FavoritesCreateDto, GenericDto> {
}
