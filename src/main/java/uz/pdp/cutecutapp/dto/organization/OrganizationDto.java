package uz.pdp.cutecutapp.dto.organization;

import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.enums.Status;

public class OrganizationDto extends GenericDto {

    public String name;

    public Long ownerId;

    public Long logoId;

    public Status status;
}
