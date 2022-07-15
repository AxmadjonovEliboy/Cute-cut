package uz.pdp.cutecutapp.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.dto.order.OrderCreateDto;
import uz.pdp.cutecutapp.dto.order.OrderDto;
import uz.pdp.cutecutapp.dto.order.OrderUpdateDto;
import uz.pdp.cutecutapp.dto.rating.RatingUpdateDto;
import uz.pdp.cutecutapp.entity.barbershop.Rating;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDto, OrderCreateDto, OrderUpdateDto> {
    Order fromUpdate(OrderUpdateDto dto, @MappingTarget Order order);

}
