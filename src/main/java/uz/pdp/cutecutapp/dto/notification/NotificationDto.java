package uz.pdp.cutecutapp.dto.notification;

import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.NotificationMessage;

public class NotificationDto extends GenericDto {

    public NotificationMessage message;

    public Long receiverId;

    public Long senderId;

    public Long orderId;
}
