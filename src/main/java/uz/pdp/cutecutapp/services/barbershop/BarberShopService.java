package uz.pdp.cutecutapp.services.barbershop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BarberShopCriteria;
import uz.pdp.cutecutapp.dto.auth.AuthDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopCreateDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.mapper.auth.AuthUserMapper;
import uz.pdp.cutecutapp.mapper.barbershop.BarberShopMapper;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;
import uz.pdp.cutecutapp.services.organization.OrganizationService;
import uz.pdp.cutecutapp.session.SessionUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BarberShopService extends AbstractService<BarberShopRepository, BarberShopMapper>
        implements GenericCrudService<BarberShop, BarberShopDto, BarberShopCreateDto, BarberShopUpdateDto, BarberShopCriteria, Long> {

    private final OrganizationService organizationService;
    private final SessionUser sessionUser;
    private final ObjectMapper objectMapper;
    private final AuthUserMapper authUserMapper;

    private final AuthUserRepository authUserRepository;

    public BarberShopService(BarberShopRepository repository, BarberShopMapper mapper, OrganizationService organizationService, SessionUser sessionUser, ObjectMapper objectMapper, AuthUserMapper authUserMapper, AuthUserRepository authUserRepository) {
        super(repository, mapper);
        this.organizationService = organizationService;
        this.sessionUser = sessionUser;
        this.objectMapper = objectMapper;
        this.authUserMapper = authUserMapper;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public DataDto<Long> create(BarberShopCreateDto createDto) {
        BarberShop barberShop = mapper.fromCreateDto(createDto);
        barberShop.setName(organizationService.getNameById(sessionUser.getOrgId()));
        BarberShop newBarbershop = repository.save(barberShop);
        return new DataDto<>(newBarbershop.getId(), 200);
    }

    @Override
    public DataDto<Boolean> delete(Long id) {

        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        } else
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
        } catch (Exception e) {
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
    public DataDto<List<BarberShopDto>> getWithCriteria(BarberShopCriteria criteria) {
        String barberShopsString = repository.findByCriteria(criteria.getLongitude(), criteria.getLatitude(), criteria.getDistance()
                /* , criteria.getSize(), criteria.getPage()*/);

        List<BarberShopDto> barberShops = new ArrayList<>();

        try {
            barberShops = objectMapper.readValue(barberShopsString,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, BarberShopDto.class));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return new DataDto<>(barberShops);
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

    public DataDto<List<AuthDto>> getBarbersByBarbershopId(Long id) {
        List<AuthUser> allByBarberShopId = authUserRepository.findAllByBarberShopId(id);
        return new DataDto<>(authUserMapper.toDto(allByBarberShopId));
    }
}
