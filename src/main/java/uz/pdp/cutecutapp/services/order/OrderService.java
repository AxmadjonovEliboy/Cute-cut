package uz.pdp.cutecutapp.services.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import uz.pdp.cutecutapp.repository.order.OrderServiceRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;
import uz.pdp.cutecutapp.services.barbershop.BarberShopService;
import uz.pdp.cutecutapp.services.barbershop.ServicesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends AbstractService<OrderRepository, OrderMapper>
        implements GenericCrudService<Order, OrderDto, OrderCreateDto, OrderUpdateDto, BaseCriteria, Long> {

    private final OrderServiceRepository orderServiceRepository;
    private final BarberShopService barberShopService;
    private final ObjectMapper objectMapper;


    public OrderService(OrderRepository repository, OrderMapper orderMapper, OrderServiceRepository orderServiceRepository, BarberShopService barberShopService, ObjectMapper objectMapper) {
        super(repository, orderMapper);
        this.orderServiceRepository = orderServiceRepository;
        this.barberShopService = barberShopService;
        this.objectMapper = objectMapper;

    }

    @Override
    public DataDto<Long> create(OrderCreateDto createDto) {
        List<uz.pdp.cutecutapp.entity.order.OrderService> orderServices = new ArrayList<>();
        Order order = mapper.fromCreateDto(createDto);
        Order save = repository.save(order);
        createDto.services.forEach(m -> orderServices.add(new uz.pdp.cutecutapp.entity.order.OrderService(save.getId(), m.getId())));
        orderServiceRepository.saveAll(orderServices);
        return new DataDto<>(save.getId(), HttpStatus.CREATED.value());
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            orderServiceRepository.deleteAllByOrderId(id);
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
        return null;
    }

    public DataDto<List<OrderDto>> getAllByUserId(Long id) {
        String ordersString = repository.findByClientIdAndDeletedFalse(id);
        List<OrderDto> orders = new ArrayList<>();
        try {
            orders = objectMapper.readValue(ordersString,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, OrderDto.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new DataDto<>(orders);
    }

    @Override
    public DataDto<OrderDto> get(Long id) {
        Optional<Order> optionalOrder = repository.findByIdAndDeletedFalse(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            OrderDto orderDto = mapper.toDto(order);
            orderDto.barbershop = barberShopService.get(order.getBarbershopId()).getData();
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
