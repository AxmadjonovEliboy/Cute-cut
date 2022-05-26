package uz.pdp.cutecutapp.dto.notification;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.cutecutapp.annotations.HaveOrder;
import uz.pdp.cutecutapp.annotations.HaveUser;
import uz.pdp.cutecutapp.dto.BaseDto;
import uz.pdp.cutecutapp.enums.NotificationMessage;


@Getter
@Setter
public class NotificationCreteDto implements BaseDto {

    private String message;

    @HaveUser
    private Long receiverId;

    @HaveUser
    private Long senderId;

    @HaveOrder
    private Long orderId;

}
