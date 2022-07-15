package uz.pdp.cutecutapp.repository.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cutecutapp.entity.faq.FAQ;
import uz.pdp.cutecutapp.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<FAQ, Long> , BaseRepository {
    @Transactional
    @Modifying
    @Query(value = "update FAQ set deleted=true where FAQ.id=:id",nativeQuery = true)
    void isDelete(@Param("id") Long id);


    List<FAQ> findAllByDeletedFalse();

    Optional<FAQ> findByIdAndDeletedFalse(Long id);
}
