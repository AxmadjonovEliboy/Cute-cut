package uz.pdp.cutecutapp.controller.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.services.barbershop.FavoritesService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavoritesService favoritesService;

    @PostMapping()
    public HttpEntity<?> addFavourites(@RequestBody FavoritesCreateDto favoritesCreateDto){
        return ResponseEntity.ok(favoritesService.create(favoritesCreateDto));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFavourite(@PathVariable Long id){
        return ResponseEntity.ok(favoritesService.get(id));
    }

    @GetMapping
    public HttpEntity<?> getFavourites(){
        return ResponseEntity.ok(favoritesService.getAll());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFavourite(@PathVariable Long id){
        return ResponseEntity.ok(favoritesService.delete(id));
    }
}
