package uz.pdp.cutecutapp.dto.order;

import uz.pdp.cutecutapp.dto.GenericDto;

public class OrderUpdateDto extends GenericDto {

    public Integer reminderTime;

    public boolean isAccepted;

    public boolean isCancelled;

    public boolean isReminder;
}
