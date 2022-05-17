package uz.pdp.cutecutapp.dto.notification;

import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.enums.NotificationMessage;

public class NotificationCreteDto implements BaseDto {

    private String message;

    private Long receiverId;

    private Long senderId;

    private Long orderId;

}
