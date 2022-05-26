package uz.pdp.cutecutapp.dto.notification;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.enums.NotificationMessage;

public class NotificationCreteDto implements BaseDto {

    public String message;

    public Long receiverId;

    public Long senderId;

    public Long orderId;

}
