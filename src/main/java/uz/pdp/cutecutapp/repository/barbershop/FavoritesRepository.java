package uz.pdp.cutecutapp.repository.barbershop;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long>, BaseRepository {
    @Query(value = "select get_favorited_barber_shop(:id);",
            nativeQuery = true)
    String getFavoritesByUserIdd(@Param("id") Long id);
}
