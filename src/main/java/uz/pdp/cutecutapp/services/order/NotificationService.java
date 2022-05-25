package uz.pdp.cutecutapp.services.order;

import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.notification.NotificationCreteDto;
import uz.pdp.cutecutapp.dto.notification.NotificationDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.order.Notification;
import uz.pdp.cutecutapp.mapper.order.NotificationMapper;
import uz.pdp.cutecutapp.repository.order.NotificationRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

public class NotificationService extends AbstractService<NotificationRepository, NotificationMapper>
        implements GenericCrudService<Notification, NotificationDto, NotificationCreteDto, GenericDto, BaseCriteria, Long> {

    public NotificationService(NotificationRepository repository, NotificationMapper mapper) {
        super(repository, mapper);
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
}
