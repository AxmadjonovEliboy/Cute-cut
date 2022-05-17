package uz.pdp.cutecutapp.mapper.order;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.order.OrderCreateDto;
import uz.pdp.cutecutapp.dto.order.OrderDto;
import uz.pdp.cutecutapp.dto.order.OrderUpdateDto;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDto, OrderCreateDto, OrderUpdateDto> {
}
