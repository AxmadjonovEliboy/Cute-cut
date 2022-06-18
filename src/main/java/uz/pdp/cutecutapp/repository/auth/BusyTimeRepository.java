package uz.pdp.cutecutapp.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.auth.BusyTime;

import java.util.List;

public interface BusyTimeRepository extends JpaRepository<BusyTime, Long> {

    List<BusyTime> findAllByBarberId(Long barberShopId);
}
