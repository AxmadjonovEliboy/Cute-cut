package uz.pdp.cutecutapp.dto.notification;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.pdp.cutecutapp.dto.BaseDto;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendDto implements BaseDto {
    public String subject;
    public String content;
    public Map<String, String> data;
    public String image;
}
