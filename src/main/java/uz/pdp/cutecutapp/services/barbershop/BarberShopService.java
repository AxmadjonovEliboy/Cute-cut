package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopCreateDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.mapper.barbershop.BarberShopMapper;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class BarberShopService extends AbstractService<BarberShopRepository, BarberShopMapper>
        implements GenericCrudService<BarberShop, BarberShopDto, BarberShopCreateDto, BarberShopUpdateDto, BaseCriteria, Long> {

    public BarberShopService(BarberShopRepository repository, BarberShopMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(BarberShopCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Void> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(BarberShopUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<BarberShopDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<BarberShopDto> get(Long id) {
        return null;
    }

    @Override
    public DataDto<List<BarberShopDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
