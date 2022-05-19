package uz.pdp.cutecutapp.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.auth.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
