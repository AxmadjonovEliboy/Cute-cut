package uz.pdp.cutecutapp.controller.file;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.file.FileStorageService;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/file")
public class AttachmentController {

    private final FileStorageService fileStorageService;

    public AttachmentController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/uploadSystem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataDto<Long>> system_upload(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(fileStorageService.storeFile(file), HttpStatus.OK);
    }

    @GetMapping("/getFileFromSystem/{id}")
    public void getFilesFromSystem(@PathVariable Long id, HttpServletResponse response) {
        fileStorageService.loadFileAsResource(id, response);
    }
}
