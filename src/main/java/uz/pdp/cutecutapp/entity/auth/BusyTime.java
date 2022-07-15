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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusyTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime starts;

    private LocalDateTime ends;

    private Long barberId;

    public BusyTime(LocalDateTime starts, LocalDateTime ends, Long barberId) {
        this.starts = starts;
        this.ends = ends;
        this.barberId = barberId;
    }
}
