package uz.pdp.cutecutapp.mapper.barbershop;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.service.ServiceCreateDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;
import uz.pdp.cutecutapp.dto.service.ServiceUpdateDto;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface ServiceMapper extends BaseMapper<uz.pdp.cutecutapp.entity.barbershop.Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto> {
}
