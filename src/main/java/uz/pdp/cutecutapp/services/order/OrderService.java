package uz.pdp.cutecutapp.services.order;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.order.OrderCreateDto;
import uz.pdp.cutecutapp.dto.order.OrderDto;
import uz.pdp.cutecutapp.dto.order.OrderUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.order.Order;
import uz.pdp.cutecutapp.mapper.order.OrderMapper;
import uz.pdp.cutecutapp.repository.order.OrderRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderService extends AbstractService<OrderRepository, OrderMapper>
        implements GenericCrudService<Order, OrderDto, OrderCreateDto, OrderUpdateDto, BaseCriteria, Long> {
    public OrderService(OrderRepository repository, OrderMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(OrderCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(OrderUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<OrderDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<OrderDto> get(Long id) {
        return null;
    }

    @Override
    public DataDto<List<OrderDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
