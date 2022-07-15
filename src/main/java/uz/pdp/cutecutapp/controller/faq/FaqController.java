package uz.pdp.cutecutapp.controller.faq;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;

import uz.pdp.cutecutapp.dto.faq.FaqCreateDto;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.faq.FaqUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.faq.FaqService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/faq/*")
public class FaqController extends AbstractController<FaqService> {


    protected FaqController(FaqService service) {
        super(service);
    }


    @GetMapping(PATH + "/all")
    public ResponseEntity<DataDto<List<FaqDto>>> faqAll(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping(PATH+"/{id}")
    public ResponseEntity<DataDto<FaqDto>> getOne(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.get(id),HttpStatus.OK);
    }

    @PostMapping(PATH+"/create")
    public ResponseEntity<DataDto<Long>> add(@Valid @RequestBody FaqCreateDto dto){
        return new ResponseEntity<>(service.create(dto),HttpStatus.OK);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody FaqUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
