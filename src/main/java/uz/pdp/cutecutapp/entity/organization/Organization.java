package uz.pdp.cutecutapp.entity.organization;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;
import uz.pdp.cutecutapp.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Organization extends Auditable {

    private String name;

    private Long ownerId;

    private Long logoId;//attachmentId

    @Enumerated(EnumType.STRING)
    private Status status;
}
