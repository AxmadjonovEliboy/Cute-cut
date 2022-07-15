package uz.pdp.cutecutapp.dto.file;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.ManagedNotifications;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadsDto {
    private String originalName;
    private String newName;
    private long size;
    private String url;
    private String contentType;
    private Resource resource;
}
