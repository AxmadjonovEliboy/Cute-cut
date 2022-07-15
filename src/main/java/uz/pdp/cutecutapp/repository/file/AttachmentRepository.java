package uz.pdp.cutecutapp.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.entity.file.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
