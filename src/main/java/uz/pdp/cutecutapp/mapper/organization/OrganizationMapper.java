package uz.pdp.cutecutapp.mapper.organization;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationUpdateDto;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto> {
}
