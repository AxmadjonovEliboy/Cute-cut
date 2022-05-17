package uz.pdp.cutecutapp.mapper.faq;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.faq.FaqCreateDto;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.faq.FaqUpdateDto;
import uz.pdp.cutecutapp.entity.faq.FAQ;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface FaqMapper extends BaseMapper<FAQ, FaqDto, FaqCreateDto, FaqUpdateDto> {
}
