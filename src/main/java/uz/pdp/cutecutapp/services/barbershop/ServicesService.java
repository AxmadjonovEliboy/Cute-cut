package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.dto.service.ServiceCreateDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;
import uz.pdp.cutecutapp.dto.service.ServiceUpdateDto;
import uz.pdp.cutecutapp.mapper.barbershop.ServiceMapper;
import uz.pdp.cutecutapp.repository.barbershop.ServiceRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class ServicesService extends AbstractService<ServiceRepository, ServiceMapper>
        implements GenericCrudService<uz.pdp.cutecutapp.entity.barbershop.Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto, BaseCriteria, Long> {
    public ServicesService(ServiceRepository repository, ServiceMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(ServiceCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Void> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(ServiceUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<ServiceDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<ServiceDto> get(Long id) {
        return null;
    }

    @Override
    public DataDto<List<ServiceDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
