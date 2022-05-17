package uz.pdp.cutecutapp.entity.auth;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.entity.Auditable;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class AuthUser extends Auditable {

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private boolean isBusy;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Status status;

    private Long pictureId;

    @Column(nullable = false)
    private Long organizationId;

    private Long barberShopId;

}
