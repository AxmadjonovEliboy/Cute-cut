package uz.pdp.cutecutapp.repository.file;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cutecutapp.entity.file.Uploads;

import java.util.Optional;

public interface UploadsRepository extends JpaRepository<Uploads, Long> {
    Optional<Uploads> findByGeneratedName(String filename);


}
