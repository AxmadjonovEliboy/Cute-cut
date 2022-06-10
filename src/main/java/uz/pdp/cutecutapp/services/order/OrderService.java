package uz.pdp.cutecutapp.services.order;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.order.OrderCreateDto;
import uz.pdp.cutecutapp.dto.order.OrderDto;
import uz.pdp.cutecutapp.dto.order.OrderUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.mapper.order.OrderMapper;
import uz.pdp.cutecutapp.repository.order.OrderRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends AbstractService<OrderRepository, OrderMapper>
        implements GenericCrudService<Order, OrderDto, OrderCreateDto, OrderUpdateDto, BaseCriteria, Long> {
    public OrderService(OrderRepository repository, OrderMapper orderMapper) {
        super(repository, orderMapper);
    }

    @Override
    public DataDto<Long> create(OrderCreateDto createDto) {
        Order order = mapper.fromCreateDto(createDto);
        Order save = repository.save(order);
        return new DataDto<>(save.getId(), HttpStatus.CREATED.value());
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<Boolean> update(OrderUpdateDto updateDto) {
        try {
            Optional<Order> optionalOrder = repository.findByIdAndDeletedFalse(updateDto.getId());
            Order order = mapper.fromUpdate(updateDto, optionalOrder.get());
            repository.save(order);
            return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }


    @Override
    public DataDto<List<OrderDto>> getAll() {
        List<OrderDto> orderDtoList = mapper.toDto(repository.findAllByDeletedFalse());
        return new DataDto<>(orderDtoList, HttpStatus.OK.value());
    }

    @Override
    public DataDto<OrderDto> get(Long id) {
        Optional<Order> optionalOrder = repository.findByIdAndDeletedFalse(id);
        if (optionalOrder.isPresent()) {
            OrderDto orderDto = mapper.toDto(optionalOrder.get());
            return new DataDto<>(orderDto, HttpStatus.OK.value());
        } else {
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, HttpStatus.NOT_FOUND));
        }
    }

    @Override
    public DataDto<List<OrderDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }
}
