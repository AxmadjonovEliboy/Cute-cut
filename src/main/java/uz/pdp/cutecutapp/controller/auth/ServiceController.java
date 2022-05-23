package uz.pdp.cutecutapp.controller.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.dto.service.ServiceCreateDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;
import uz.pdp.cutecutapp.dto.service.ServiceUpdateDto;
import uz.pdp.cutecutapp.services.barbershop.ServicesService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ServicesService servicesService;

    @PostMapping
    public HttpEntity<?> addService(@RequestBody ServiceCreateDto createDto){
        return ResponseEntity.ok(servicesService.create(createDto));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getService(@PathVariable Long id){
        return ResponseEntity.ok(servicesService.get(id));
    }

    @GetMapping
    public HttpEntity<?> getServices(){
        return ResponseEntity.ok(servicesService.getAll());
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editService(@RequestBody ServiceUpdateDto serviceUpdateDto){
        return ResponseEntity.ok(servicesService.update(serviceUpdateDto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteService(@PathVariable Long id){
        return ResponseEntity.ok(servicesService.delete(id));
    }
}
