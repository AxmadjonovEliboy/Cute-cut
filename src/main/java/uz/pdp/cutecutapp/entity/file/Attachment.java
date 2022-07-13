package uz.pdp.cutecutapp.entity.file;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileOriginalName; // pdp.jpg

    private long size; // 2048000

    private String contentType; // image/jpg kabilar

    @Column(unique = true) // doim unique bo'ladi bu
    // serverda turgan faylning nomi
    private String name;

}
