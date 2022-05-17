package uz.pdp.cutecutapp.repository.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.faq.FAQ;

public interface FaqRepository extends JpaRepository<FAQ, Long> {
}
