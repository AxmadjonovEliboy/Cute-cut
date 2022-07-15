package uz.pdp.cutecutapp.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String deviceId;

    private String deviceToken;

    public Device(Long userId, String deviceId, String deviceToken) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.deviceToken = deviceToken;
    }
}
