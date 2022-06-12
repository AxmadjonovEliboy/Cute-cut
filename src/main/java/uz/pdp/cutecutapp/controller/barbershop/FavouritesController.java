package uz.pdp.cutecutapp.controller.barbershop;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.barbershop.FavoritesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouritesController extends AbstractController<FavoritesService> {

    private final FavoritesService favoritesService;

    protected FavouritesController(FavoritesService service, FavoritesService favoritesService) {
        super(service);
        this.favoritesService = favoritesService;
    }

    @PostMapping(PATH + "/add")
    public HttpEntity<DataDto<Long>> addFavourites(@RequestBody @Valid FavoritesCreateDto favoritesCreateDto) {
        return ResponseEntity.ok(favoritesService.create(favoritesCreateDto));
    }

    @GetMapping(PATH + "/get/{id}")
    public HttpEntity<DataDto<FavoritesDto>> getFavourite(@PathVariable Long id) {
        return ResponseEntity.ok(favoritesService.get(id));
    }

    @GetMapping(PATH + "/get/allUserFavorites/{id}")
    public HttpEntity<DataDto<List<BarberShopDto>>> getFavourites(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new DataDto<>(favoritesService.getAllByUserId(id)));
    }

    @DeleteMapping(PATH + "/delete/{id}")
    public HttpEntity<DataDto<Boolean>> deleteFavourite(@PathVariable Long id) {
        return ResponseEntity.ok(favoritesService.delete(id));
    }
}
