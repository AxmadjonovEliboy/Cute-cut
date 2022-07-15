package uz.pdp.cutecutapp.controller.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.faq.FaqUpdateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.organization.OrganizationService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/organization/*")
public class OrganizationController extends AbstractController<OrganizationService> {


    protected OrganizationController(OrganizationService service) {
        super(service);
    }

    @PostMapping(PATH+"/create")
    public ResponseEntity<DataDto<Long>> add(@Valid @RequestBody OrganizationCreateDto dto){
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody OrganizationUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @PatchMapping(PATH+"/block/{id}")
    public ResponseEntity<DataDto<Boolean>> block(@PathVariable Long id){
        return new ResponseEntity<>(service.block(id),HttpStatus.OK);
    }

    @PatchMapping(PATH+"/unblock/{id}")
    public ResponseEntity<DataDto<Boolean>> unblock(@PathVariable Long id){
        return new ResponseEntity<>(service.unblock(id),HttpStatus.OK);
    }

    @PatchMapping(PATH+"/deadline/{id}")
    public ResponseEntity<DataDto<Boolean>> deadline(@PathVariable Long id,@RequestBody Date date){
        return new ResponseEntity<>(service.deadline(id,date),HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/all")
    public ResponseEntity<DataDto<List<OrganizationDto>>> organizationAll(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping(PATH+"/{id}")
    public ResponseEntity<DataDto<OrganizationDto>> getOne(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(service.get(id),HttpStatus.OK);
    }

    @GetMapping(PATH+"/allByAdminId/{id}")
    public ResponseEntity<DataDto<List<OrganizationDto>>> adminIdOrganizationAll(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(service.getAdminIdOrganizationAll(id),HttpStatus.OK);
    }

}
