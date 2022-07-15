package uz.pdp.cutecutapp.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.auth.AuthCreateDto;
import uz.pdp.cutecutapp.dto.auth.AuthDto;
import uz.pdp.cutecutapp.dto.auth.AuthUpdateDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.mapper.BaseMapper;


@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthUserMapper extends BaseMapper<AuthUser, AuthDto, AuthCreateDto, AuthUpdateDto> {

    AuthUser updateFrom(AuthUpdateDto updateDto , @MappingTarget AuthUser authUser);

    @Override
    AuthUser fromCreateDto(AuthCreateDto authCreateDto);
}
