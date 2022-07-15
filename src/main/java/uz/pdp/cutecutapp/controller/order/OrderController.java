package uz.pdp.cutecutapp.controller.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.order.OrderCreateDto;
import uz.pdp.cutecutapp.dto.order.OrderDto;
import uz.pdp.cutecutapp.dto.order.OrderUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.order.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order/*")
public class OrderController extends AbstractController<OrderService> {

    protected OrderController(OrderService service) {
        super(service);
    }

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody OrderCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PutMapping(PATH + "/update")
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody OrderUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<OrderDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/listByUserId/passed/{id}")
    public ResponseEntity<DataDto<List<OrderDto>>> getPassedListByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getAllPassedByUserId(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/listByUserId/upcoming/{id}")
    public ResponseEntity<DataDto<List<OrderDto>>> getUpcomingListByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getAllUpcomingByUserId(id), HttpStatus.OK);
    }

}
