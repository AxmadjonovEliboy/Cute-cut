package uz.pdp.cutecutapp.entity.faq;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class FAQ extends Auditable {

    private String title;

    private String description;

}
