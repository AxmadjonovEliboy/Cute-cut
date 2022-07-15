package uz.pdp.cutecutapp.services.file;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cutecutapp.dto.file.UploadsDto;
import uz.pdp.cutecutapp.entity.file.Uploads;
import uz.pdp.cutecutapp.repository.file.UploadsRepository;
import uz.pdp.cutecutapp.services.BaseService;
import uz.pdp.cutecutapp.session.SessionUser;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;


@Slf4j
@Service("fileService")
public class FileStorageService implements BaseService {
    public static final String UNICORN_UPLOADS_B_4_LIB = "\\home\\jarvis\\uploads\\";
    public static final Path PATH = Paths.get(UNICORN_UPLOADS_B_4_LIB);

    private final SessionUser sessionUser;
    private final UploadsRepository repository;

    public FileStorageService(SessionUser sessionUser, UploadsRepository repository) {
        this.sessionUser = sessionUser;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (!Files.exists(PATH)) {
            try {
                Files.createDirectories(PATH);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }
    }


    public String store(@NonNull MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String generatedName = String.format("%s.%s", System.currentTimeMillis(), extension);
            Path rootPath = Paths.get(UNICORN_UPLOADS_B_4_LIB, generatedName);
            Files.copy(file.getInputStream(), rootPath, StandardCopyOption.REPLACE_EXISTING);
            Uploads uploadedFile = new Uploads(originalFilename, generatedName, file.getContentType(), (UNICORN_UPLOADS_B_4_LIB + generatedName), file.getSize());
            Uploads saveFile = repository.save(uploadedFile);

            return generatedName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public UploadsDto loadResource(@NonNull String fileName) {
        Optional<Uploads> uploads = repository.findByGeneratedName(fileName);

        if (!uploads.isPresent()) {
            try {
                throw new NoSuchFileException("not found");
            } catch (NoSuchFileException e) {
                throw new RuntimeException(e);
            }
        }

        FileSystemResource resource = new FileSystemResource(UNICORN_UPLOADS_B_4_LIB + fileName);
        return UploadsDto.builder()
                .resource(resource)
                .originalName(uploads.get().getOriginalName())
                .newName(uploads.get().getGeneratedName())
                .contentType(uploads.get().getContentType())
                .size(uploads.get().getSize())
                .build();
    }

}
