package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopCreateDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.mapper.barbershop.BarberShopMapper;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BarberShopService extends AbstractService<BarberShopRepository, BarberShopMapper>
        implements GenericCrudService<BarberShop, BarberShopDto, BarberShopCreateDto, BarberShopUpdateDto, BaseCriteria, Long> {

    public BarberShopService(BarberShopRepository repository, BarberShopMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(BarberShopCreateDto createDto) {
        BarberShop barberShop = mapper.fromCreateDto(createDto);
        BarberShop newBarbershop = repository.save(barberShop);
        return new DataDto<>(newBarbershop.getId(), 200);
    }

    @Override
    public DataDto<Boolean> delete(Long id) {

        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        }
        else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/rating/delete"
                    , HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<Boolean> update(BarberShopUpdateDto updateDto) {
        try {

        Optional<BarberShop> optionalBarberShop = repository.findByIdAndDeletedFalse(updateDto.getId());
        BarberShop barberShop = mapper.fromUpdate(updateDto, optionalBarberShop.get());
        repository.save(barberShop);
        return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
        }
        catch (Exception e){
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public DataDto<List<BarberShopDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<BarberShopDto> get(Long id) {
        Optional<BarberShop> optionalBarberShop = repository.findByIdAndDeletedFalse(id);
        if (optionalBarberShop.isPresent()) {
            BarberShopDto barberShopDto = mapper.toDto(repository.getById(id));
            return new DataDto<>(barberShopDto, 200);
        } else {
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/rating/getById",
                    HttpStatus.NOT_FOUND));
        }

    }

    @Override
    public DataDto<List<BarberShopDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }

    public DataDto<List<BarberShopDto>> getByOrganizationId(Long id) {
        List<BarberShop> barberShops = repository.findByOrgIdAndDeletedFalse(id);
        List<BarberShopDto> barberShopDtos = mapper.toDto(barberShops);
        return new DataDto<>(barberShopDtos, 200);
    }

    public DataDto<List<BarberShopDto>> getAllBarbershops() {
        List<BarberShop> all = repository.findAll();
        return new DataDto<>(mapper.toDto(all));
    }
}
