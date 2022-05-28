package uz.pdp.cutecutapp.controller.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cutecutapp.controller.AbstractController;
import uz.pdp.cutecutapp.dto.notification.NotificationCreteDto;
import uz.pdp.cutecutapp.dto.notification.NotificationDto;
import uz.pdp.cutecutapp.dto.rating.RatingDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.services.order.NotificationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notification/*")
public class NotificationController extends AbstractController<NotificationService> {

    protected NotificationController(NotificationService service) {
        super(service);
    }

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody NotificationCreteDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<Boolean>> deleted(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<DataDto<NotificationDto>> get(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(PATH + "/list")
    public ResponseEntity<DataDto<List<NotificationDto>>> getList() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping(PATH + "/getAllOwnNotification/{receiverId}")
    public ResponseEntity<DataDto<List<NotificationDto>>> getAll(@PathVariable Long receiverId) {
        return new ResponseEntity<>(service.getNotifications(receiverId), HttpStatus.OK);
    }
}

