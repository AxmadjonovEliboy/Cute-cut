package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.dto.service.ServiceCreateDto;
import uz.pdp.cutecutapp.dto.service.ServiceDto;
import uz.pdp.cutecutapp.dto.service.ServiceUpdateDto;
import uz.pdp.cutecutapp.exception.NotFoundException;
import uz.pdp.cutecutapp.mapper.barbershop.ServiceMapper;
import uz.pdp.cutecutapp.repository.barbershop.ServiceRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService extends AbstractService<ServiceRepository, ServiceMapper>
        implements GenericCrudService<uz.pdp.cutecutapp.entity.barbershop.Service, ServiceDto, ServiceCreateDto, ServiceUpdateDto, BaseCriteria, Long> {
    public ServicesService(ServiceRepository repository, ServiceMapper mapper, ServiceRepository serviceRepository) {
        super(repository, mapper);
        this.serviceRepository = serviceRepository;
    }

    private final ServiceRepository serviceRepository;

    @Override
    public DataDto<Long> create(ServiceCreateDto createDto) {
        uz.pdp.cutecutapp.entity.barbershop.Service service = mapper.fromCreateDto(createDto);
        serviceRepository.save(service);
        return new DataDto<>(service.getId());
    }

    @Override
    public DataDto<Boolean> delete(Long id) {

        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        } else {
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/service/delete", HttpStatus.NOT_FOUND));
        }
//        Optional<uz.pdp.cutecutapp.entity.barbershop.Service> optionalService = serviceRepository.findById(id);
//        if (optionalService.isPresent()) {
//            serviceRepository.deleteById(id);
//            return new DataDto<>();
//        }
//        throw new NotFoundException("Service not found!");
    }

    @Override
    public DataDto<Boolean> update(ServiceUpdateDto updateDto) {
        uz.pdp.cutecutapp.entity.barbershop.Service service = mapper.fromUpdateDto(updateDto);
        Optional<uz.pdp.cutecutapp.entity.barbershop.Service> optionalService = serviceRepository.findById(service.getId());
        if (optionalService.isPresent()) {
            uz.pdp.cutecutapp.entity.barbershop.Service service1 = optionalService.get();
            service1.setPrice(service.getPrice());
            service1.setType(service.getType());
            service1.setDeleted(updateDto.isDeleted);
            service1.setBarberShopId(service.getBarberShopId());
            serviceRepository.save(service1);
            return new DataDto<>(true);
        }
        throw new NotFoundException("Service not found");
    }

    @Override
    public DataDto<List<ServiceDto>> getAll() {
        List<uz.pdp.cutecutapp.entity.barbershop.Service> services = serviceRepository.findAll();
        return new DataDto<>(mapper.toDto(services));
    }


    @Override
    public DataDto<ServiceDto> get(Long id) {
        Optional<uz.pdp.cutecutapp.entity.barbershop.Service> optionalService = serviceRepository.findByIdAndDeletedFalse(id);
        if (optionalService.isPresent()) {
            ServiceDto serviceDto = mapper.toDto(optionalService.get());
            return new DataDto<>(serviceDto);
        }
        throw new NotFoundException("Service not found");
    }

    @Override
    public DataDto<List<ServiceDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }

    public DataDto<List<ServiceDto>> getByBarberShopId(Long id) {
        List<uz.pdp.cutecutapp.entity.barbershop.Service> services = serviceRepository.findAllByBarberShopIdAndDeletedFalse(id);
        List<ServiceDto> serviceDtos = mapper.toDto(services);
        return new DataDto<>(serviceDtos, HttpStatus.OK.value());
    }
}
