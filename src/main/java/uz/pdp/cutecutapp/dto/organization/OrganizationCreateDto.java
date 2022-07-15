package uz.pdp.cutecutapp.dto.organization;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.pdp.cutecutapp.annotations.HaveUser;
import uz.pdp.cutecutapp.dto.BaseDto;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationCreateDto implements BaseDto {
    @NotBlank
    public String name;
    @HaveUser
    public Long ownerId;



}
