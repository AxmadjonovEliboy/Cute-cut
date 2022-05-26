package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.barbershop.Service;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface ServiceRepository extends JpaRepository<Service, Long>, BaseRepository {
    boolean existsByType(String type);
}
