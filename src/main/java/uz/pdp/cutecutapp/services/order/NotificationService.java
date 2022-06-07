package uz.pdp.cutecutapp.services.order;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.notification.NotificationCreteDto;
import uz.pdp.cutecutapp.dto.notification.NotificationDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.order.Notification;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.mapper.order.NotificationMapper;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.order.NotificationRepository;
import uz.pdp.cutecutapp.repository.order.OrderRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService extends AbstractService<NotificationRepository, NotificationMapper>
        implements GenericCrudService<Notification, NotificationDto, NotificationCreteDto, GenericDto, BaseCriteria, Long> {

    public NotificationService(NotificationRepository repository, NotificationMapper mapper, OrderRepository orderRepository, AuthUserRepository authUserRepository) {
        super(repository, mapper);
        this.orderRepository = orderRepository;
        this.authUserRepository = authUserRepository;
    }

    private final OrderRepository orderRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public DataDto<Long> create(NotificationCreteDto createDto) {
        Optional<AuthUser> optionalSender = authUserRepository.findById(createDto.getSenderId());
        if (!optionalSender.isPresent()) {
            return new DataDto<>(new AppErrorDto("Sender not found with id : " + createDto.getSenderId(), HttpStatus.NOT_FOUND));
        }
        Optional<AuthUser> optionalReceiver = authUserRepository.findById(createDto.getReceiverId());
        if (!optionalReceiver.isPresent()) {
            return new DataDto<>(new AppErrorDto("Receiver not found with id : " + createDto.getReceiverId(), HttpStatus.NOT_FOUND));
        }
        Optional<Order> optionalOrder = orderRepository.findByIdAndDeletedFalse(createDto.getOrderId());
        if (!optionalOrder.isPresent()) {
            return new DataDto<>(new AppErrorDto("Order not found with id : " + createDto.getOrderId(), HttpStatus.NOT_FOUND));
        }
        Notification notification = mapper.fromCreateDto(createDto);
        Notification save = repository.save(notification);
        return new DataDto<>(save.getId(), HttpStatus.CREATED.value());
    }


    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<Boolean> update(GenericDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<NotificationDto>> getAll() {
        List<NotificationDto> notificationDtoList = mapper.toDto(repository.findAllByDeletedFalse());
        return new DataDto<>(notificationDtoList, HttpStatus.OK.value());
    }

    @Override
    public DataDto<NotificationDto> get(Long id) {
        Optional<Notification> optionalNotification = repository.findByIdAndDeletedFalse(id);
        if (!optionalNotification.isPresent()) {
            NotificationDto notificationDto = mapper.toDto(optionalNotification.get());
            return new DataDto<>(notificationDto, HttpStatus.OK.value());
        } else {
            return new DataDto<>(new AppErrorDto("Order not found with id : " + id, HttpStatus.NOT_FOUND));
        }
    }

    @Override
    public DataDto<List<NotificationDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }

    public DataDto<List<NotificationDto>> getNotifications(Long id) {
        List<Notification> notificationByReceiverId = repository.getNotificationByReceiverId(id);
        List<NotificationDto> notificationDtoList = mapper.toDto(notificationByReceiverId);
        return new DataDto<>(notificationDtoList, HttpStatus.OK.value());
    }
}
