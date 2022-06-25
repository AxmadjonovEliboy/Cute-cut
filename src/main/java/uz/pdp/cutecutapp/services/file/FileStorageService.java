package uz.pdp.cutecutapp.services.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cutecutapp.dto.file.UploadsDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.file.Uploads;
import uz.pdp.cutecutapp.properties.FileStorageProperties;
import uz.pdp.cutecutapp.repository.file.UploadsRepository;
import uz.pdp.cutecutapp.services.BaseService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileStorageService implements BaseService {

    private final Path fileStorageLocation;
    private final UploadsRepository uploadsRepository;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, UploadsRepository uploadsRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.uploadsRepository = uploadsRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public DataDto<Uploads> storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            String originalFilename = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String generatedName = String.format("%s.%s", System.currentTimeMillis(), extension);
            Uploads uploads = new Uploads(originalFilename, generatedName, file.getContentType(), path.toString(), file.getSize());
            uploadsRepository.save(uploads);
            return new DataDto<>(uploads);
        } catch (IOException ex) {
            return new DataDto<>(new AppErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not store file " + fileName + ". Please try again!"));
        }
    }

    public DataDto<Resource> loadFileAsResource(String fileName) {
        try {
            Optional<Uploads> byGeneratedName = uploadsRepository.findByGeneratedName(fileName);
            Uploads uploads = byGeneratedName.get();
            Path filePath = this.fileStorageLocation.resolve(uploads.getOriginalName()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return new DataDto<>(resource);
            } else {
                return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND.value(), "File not found " + fileName));
            }
        } catch (MalformedURLException ex) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND.value(), "File not found " + fileName));
        }
    }
}
