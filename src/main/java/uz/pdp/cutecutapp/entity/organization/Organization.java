package uz.pdp.cutecutapp.entity.organization;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.cutecutapp.entity.Auditable;
import uz.pdp.cutecutapp.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Organization extends Auditable {

    private String name;

    private Long ownerId;

    private Long logoId;//attachmentId

    @Enumerated(EnumType.STRING)
    private Status status=Status.DEFAULT;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date deadline;


}
