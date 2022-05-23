package uz.pdp.cutecutapp.repository.barbershop;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> , BaseRepository {
}
