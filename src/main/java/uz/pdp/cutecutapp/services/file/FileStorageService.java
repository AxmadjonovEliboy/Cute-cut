package uz.pdp.cutecutapp.services.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.file.Attachment;
import uz.pdp.cutecutapp.properties.FileStorageProperties;
import uz.pdp.cutecutapp.repository.file.AttachmentRepository;
import uz.pdp.cutecutapp.services.BaseService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService implements BaseService {

    private final AttachmentRepository attachmentRepository;
    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageService(AttachmentRepository attachmentRepository, FileStorageProperties fileStorageProperties) {
        this.attachmentRepository = attachmentRepository;
        this.fileStorageProperties = fileStorageProperties;
        try {
            Files.createDirectories(Paths.get(fileStorageProperties.getUploadDir()));
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }

    }

    public DataDto<Long> storeFile(MultipartFile file) {
        try {
            long size = file.getSize();
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment();
            attachment.setSize(size);
            attachment.setFileOriginalName(originalFilename);
            attachment.setContentType(contentType);
            String[] split = originalFilename.split("\\.");
            String name = UUID.randomUUID() + "." + split[split.length - 1];
            attachment.setName(name);

            Attachment save = attachmentRepository.save(attachment);

            Path path = Paths.get(fileStorageProperties.getUploadDir()
                    + File.separator + name);
            Files.copy(file.getInputStream(), path);
            return new DataDto<>(save.getId(), HttpStatus.CREATED.value());
        } catch (IOException e) {
            e.printStackTrace();
            return new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    public void loadFileAsResource(Long id, HttpServletResponse response) {

        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();
            response.setHeader("Content-Disposition",
                    "attachment; filename = \""
                            + attachment.getFileOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            try {
                FileInputStream fileInputStream = new FileInputStream(fileStorageProperties.getUploadDir()
                        + File.separator + attachment.getName());
                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Content Not found with id : " + id);
    }
}
