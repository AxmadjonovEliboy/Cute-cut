package uz.pdp.cutecutapp.entity.file;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] bytes; //asosiy content

    @OneToOne // foreign key bo'ladi unga bosganda uni contentini ham op  keladi
    private Attachment attachment;
}
