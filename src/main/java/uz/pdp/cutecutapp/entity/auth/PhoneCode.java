package uz.pdp.cutecutapp.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author Axmadjonov Eliboy on Mon 14:43. 23/05/22
 * @project cute-cut-app
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhoneCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    private String code;
    private LocalDateTime expiration;


    public PhoneCode(String phoneNumber, String code, LocalDateTime expiration) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.expiration = expiration;
    }
}
