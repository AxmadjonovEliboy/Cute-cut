package uz.pdp.cutecutapp.mapper.barbershop;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopCreateDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopUpdateDto;
import uz.pdp.cutecutapp.dto.rating.RatingUpdateDto;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.entity.barbershop.Rating;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface BarberShopMapper extends BaseMapper<BarberShop, BarberShopDto, BarberShopCreateDto, BarberShopUpdateDto> {
    BarberShop fromUpdate(BarberShopUpdateDto dto, @MappingTarget BarberShop barberShop);
}
