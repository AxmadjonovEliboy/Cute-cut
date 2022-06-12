package uz.pdp.cutecutapp.controller.rating;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.rating.RatingCreateDto;
import uz.pdp.cutecutapp.dto.rating.RatingDto;
import uz.pdp.cutecutapp.dto.rating.RatingUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.barbershop.RatingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rating/*")
public class RatingController extends AbstractController<RatingService> {


    protected RatingController(RatingService service) {
        super(service);
    }

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody @Valid RatingCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody RatingUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<RatingDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/getAllByBarBerShopId/{id}")
    public ResponseEntity<DataDto<List<RatingDto>>> getAll(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.getByBarberShopId(id), HttpStatus.OK);
    }
}
