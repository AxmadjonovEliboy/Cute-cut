package uz.pdp.cutecutapp.mapper.order;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.notification.NotificationCreteDto;
import uz.pdp.cutecutapp.dto.notification.NotificationDto;
import uz.pdp.cutecutapp.entity.order.Notification;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface NotificationMapper extends BaseMapper<Notification, NotificationDto, NotificationCreteDto, GenericDto> {
}
