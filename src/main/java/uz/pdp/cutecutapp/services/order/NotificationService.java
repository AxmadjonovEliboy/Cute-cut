package uz.pdp.cutecutapp.services.order;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.notification.NotificationCreteDto;
import uz.pdp.cutecutapp.dto.notification.NotificationDto;
import uz.pdp.cutecutapp.dto.notification.NotificationSendDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.order.Notification;
import uz.pdp.cutecutapp.mapper.order.NotificationMapper;
import uz.pdp.cutecutapp.repository.order.NotificationRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class NotificationService extends AbstractService<NotificationRepository, NotificationMapper>
        implements GenericCrudService<Notification, NotificationDto, NotificationCreteDto, GenericDto, BaseCriteria, Long> {
    
    private final FirebaseMessaging firebaseMessaging;
    
    public NotificationService(NotificationRepository repository, NotificationMapper mapper, FirebaseMessaging firebaseMessaging) {
        super(repository, mapper);
        this.firebaseMessaging = firebaseMessaging;
    }
    
    @Override
    public DataDto<Long> create(NotificationCreteDto createDto) {
        return null;
    }
    
    @Override
    public DataDto<Boolean> delete(Long id) {
        return null;
    }
    
    @Override
    public DataDto<Boolean> update(GenericDto updateDto) {
        return null;
    }
    
    @Override
    public DataDto<List<NotificationDto>> getAll() {
        return null;
    }
    
    @Override
    public DataDto<NotificationDto> get(Long id) {
        return null;
    }
    
    @Override
    public DataDto<List<NotificationDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
    
    /**
     *
     * @param dto  Jo'natiladigan notification'ning ma'lumotlari
     * @param token jo'natilishi kerak bo'lgan device id'si
     * @return
     * @throws FirebaseMessagingException
     */
    public String sendNotification(NotificationSendDto dto, String token) throws FirebaseMessagingException {
        
        com.google.firebase.messaging.Notification notification = com.google.firebase.messaging.Notification
                .builder()
                .setTitle(dto.subject)
                .setBody(dto.content)
                .setImage(dto.image)
                .build();
        
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(dto.data)
                .build();
        
        return firebaseMessaging.send(message);
    }
}
