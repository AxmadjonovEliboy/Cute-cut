package uz.pdp.cutecutapp.controller.barbershop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.criteria.BarberShopCriteria;
import uz.pdp.cutecutapp.dto.auth.AuthDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopCreateDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.services.barbershop.BarberShopService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/barbershop/*")
public class BarbershopController extends AbstractController<BarberShopService> {

    protected BarbershopController(BarberShopService service) {
        super(service);
    }

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody BarberShopCreateDto createDto) {
        return new ResponseEntity<>(service.create(createDto), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getAll")
    public ResponseEntity<DataDto<List<BarberShopDto>>> get() {
        return new ResponseEntity<>(service.getAllBarbershops(), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @PatchMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody BarberShopUpdateDto updateDtoto) {
        return new ResponseEntity<>(service.update(updateDtoto), HttpStatus.OK);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<BarberShopDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getAllByOrganizationId/{id}")
    public ResponseEntity<DataDto<List<BarberShopDto>>> getAll(@PathVariable Long id) {
        return new ResponseEntity<>(service.getByOrganizationId(id), HttpStatus.OK);
    }

    @PostMapping(PATH + "/getByCriteria")
    public ResponseEntity<DataDto<List<BarberShopDto>>> getByCriteria(@RequestBody BarberShopCriteria criteria) {
        return new ResponseEntity<>(service.getWithCriteria(criteria), HttpStatus.OK);
    }

    @GetMapping(PATH + "/barbers/{id}")
    public ResponseEntity<DataDto<List<AuthDto>>> getBarbersByBarbershopId(@PathVariable Long id){
            return new ResponseEntity<>(service.getBarbersByBarbershopId(id), HttpStatus.OK);
    }
}
