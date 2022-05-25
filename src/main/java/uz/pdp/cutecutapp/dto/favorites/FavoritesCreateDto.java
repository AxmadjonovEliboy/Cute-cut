package uz.pdp.cutecutapp.dto.favorites;

import uz.pdp.cutecutapp.annotations.HaveBarberShop;
import uz.pdp.cutecutapp.annotations.HaveUser;
import uz.pdp.cutecutapp.dto.BaseDto;

public class FavoritesCreateDto implements BaseDto {

    @HaveUser
    public Long clientId;

    @HaveBarberShop
    public Long barberShopId;
}
