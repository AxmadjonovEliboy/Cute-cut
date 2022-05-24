package uz.pdp.cutecutapp.repository.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.faq.FAQ;
import uz.pdp.cutecutapp.repository.BaseRepository;

public interface FaqRepository extends JpaRepository<FAQ, Long> , BaseRepository {
}
