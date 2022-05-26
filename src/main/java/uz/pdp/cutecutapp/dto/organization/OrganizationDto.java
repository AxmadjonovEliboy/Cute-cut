package uz.pdp.cutecutapp.dto.organization;

import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Status;

import java.util.Date;

public class OrganizationDto extends GenericDto {

    public String name;

    public Long ownerId;

    public Long logoId;

    private Date deadline;

    private Status status;
}
