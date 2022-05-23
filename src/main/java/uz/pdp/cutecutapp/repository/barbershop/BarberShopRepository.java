package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface BarberShopRepository extends JpaRepository<BarberShop, Long> , BaseRepository {
}
