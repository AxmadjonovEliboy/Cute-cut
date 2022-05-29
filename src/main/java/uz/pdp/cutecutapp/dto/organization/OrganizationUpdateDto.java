package uz.pdp.cutecutapp.dto.organization;

import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Status;

import java.util.Date;

public class OrganizationUpdateDto extends GenericDto {

    public String name;

    public Long logoId;

    public Date deadline;

    public Status status;
}
