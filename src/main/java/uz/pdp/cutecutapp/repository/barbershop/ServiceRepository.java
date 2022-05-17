package uz.pdp.cutecutapp.repository.barbershop;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.barbershop.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
