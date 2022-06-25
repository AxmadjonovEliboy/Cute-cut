package uz.pdp.cutecutapp.controller.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.file.Uploads;
import uz.pdp.cutecutapp.services.file.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController extends AbstractController<FileStorageService> {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    protected FileController(FileStorageService service) {
        super(service);
    }


    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataDto<Uploads>> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.storeFile(file), HttpStatus.OK);

    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        DataDto<Resource> resourceDataDto = service.loadFileAsResource(fileName);
        if (resourceDataDto.isSuccess()) {
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resourceDataDto.getData().getFile().getAbsolutePath());
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceDataDto.getData().getFilename() + "\"")
                    .body(resourceDataDto.getData());
        } else
            return ResponseEntity.ok().body(null);

    }

}
