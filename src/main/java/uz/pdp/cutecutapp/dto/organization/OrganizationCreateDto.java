package uz.pdp.cutecutapp.dto.organization;

import uz.pdp.cutecutapp.annotations.HaveUser;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class OrganizationCreateDto implements BaseDto {
    @NotBlank
    public String name;
    @HaveUser
    public Long ownerId;



}
