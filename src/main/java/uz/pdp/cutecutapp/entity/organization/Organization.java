package uz.pdp.cutecutapp.entity.organization;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Organization extends Auditable {

    private String name;

    private Long ownerId;

    private Long logoId;//attachmentId
}
