package uz.pdp.cutecutapp.mapper.auth;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.auth.AuthCreateDto;
import uz.pdp.cutecutapp.dto.auth.AuthDto;
import uz.pdp.cutecutapp.dto.auth.AuthUpdateDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.mapper.BaseMapper;


@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser, AuthDto, AuthCreateDto, AuthUpdateDto> {

}
