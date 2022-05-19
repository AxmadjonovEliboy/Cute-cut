package uz.pdp.cutecutapp.controller.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cutecutapp.controller.base.AbstractController;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.faq.FaqService;

import java.util.List;

import static uz.pdp.cutecutapp.services.file.FileStorageService.PATH;

@RestController
@RequiredArgsConstructor
public class FaqController extends AbstractController {
    private final FaqService faqService;

    @GetMapping(PATH + "/faq/all")
    public ResponseEntity<DataDto<FaqDto>> faqAll(){
        DataDto<List<FaqDto>> all = faqService.getAll();
        return null;
    }


}
