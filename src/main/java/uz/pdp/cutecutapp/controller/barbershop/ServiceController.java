package uz.pdp.cutecutapp.controller.barbershop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.rating.RatingDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.dto.service.ServiceCreateDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;
import uz.pdp.cutecutapp.dto.service.ServiceUpdateDto;
import uz.pdp.cutecutapp.services.barbershop.FavoritesService;
import uz.pdp.cutecutapp.services.barbershop.ServicesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController extends AbstractController<FavoritesService> {

    private final ServicesService servicesService;

    protected ServiceController(FavoritesService service, ServicesService servicesService) {
        super(service);
        this.servicesService = servicesService;
    }

    @PostMapping(PATH + "/add")
    public HttpEntity<DataDto<Long>> addService(@RequestBody @Valid ServiceCreateDto createDto){
        return ResponseEntity.ok(servicesService.create(createDto));
    }

    @GetMapping(PATH + "/get/{id}")
    public HttpEntity<DataDto<ServiceDto>> getService(@PathVariable Long id){
        return ResponseEntity.ok(servicesService.get(id));
    }

//    @GetMapping(PATH + "/service/get/all")
//    public HttpEntity<DataDto<List<ServiceDto>>> getServices(){
//        return ResponseEntity.ok(servicesService.getAll());
//    }

    @GetMapping(PATH + "/getAllByBarBerShopId/{id}")
    public ResponseEntity<DataDto<List<ServiceDto>>> getAll(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(servicesService.getByBarberShopId(id), HttpStatus.OK);
    }
    @PutMapping(PATH + "/edit/{id}")
    public HttpEntity<DataDto<Boolean>> editService(@RequestBody @Valid ServiceUpdateDto serviceUpdateDto){
        return ResponseEntity.ok(servicesService.update(serviceUpdateDto));
    }

    @DeleteMapping(PATH + "/delete/{id}")
    public HttpEntity<DataDto<Boolean>> deleteService(@PathVariable Long id){
        return ResponseEntity.ok(servicesService.delete(id));
    }
}
