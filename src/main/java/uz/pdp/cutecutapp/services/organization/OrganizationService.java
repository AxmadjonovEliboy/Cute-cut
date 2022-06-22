package uz.pdp.cutecutapp.services.organization;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.enums.Role;
import uz.pdp.cutecutapp.enums.Status;
import uz.pdp.cutecutapp.mapper.organization.OrganizationMapper;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.repository.organization.OrganizationRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper>
        implements GenericCrudService<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto, BaseCriteria, Long> {

    private final AuthUserRepository authUserRepository;

    private final BarberShopRepository barberShopRepository;
    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, AuthUserRepository authUserRepository, BarberShopRepository barberShopRepository) {
        super(repository, mapper);

        this.authUserRepository = authUserRepository;
        this.barberShopRepository = barberShopRepository;
    }


    @Override
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        Organization organization = mapper.fromCreateDto(createDto);
        Organization newOrg = repository.save(organization);
        return new DataDto<>(newOrg.getId(), HttpStatus.CREATED.value());
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            repository.isDelete(id);
            return new DataDto<>(Boolean.TRUE, HttpStatus.NO_CONTENT.value());
        }
        return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/organization/delete", HttpStatus.NOT_FOUND));

    }

    @Override
    public DataDto<Boolean> update(OrganizationUpdateDto updateDto) {
        try {
            Optional<Organization> optionalOrganization = repository.findByIdAndDeletedFalse(updateDto.getId());
            Organization newOrganization = mapper.fromUpdate(updateDto, optionalOrganization.get());
            repository.save(newOrganization);
            return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public DataDto<List<OrganizationDto>> getAll() {
        List<Organization> organizationList = repository.findAllByAndDeletedFalse();
        List<OrganizationDto> organizationDtos = mapper.toDto(organizationList);
        return new DataDto<>(organizationDtos, HttpStatus.OK.value());
    }

    @Override
    public DataDto<OrganizationDto> get(Long id) {
        Optional<Organization> optionalOrganization = repository.findByIdAndDeletedFalse(id);
        if (optionalOrganization.isPresent()) {
            OrganizationDto organizationDto = mapper.toDto(optionalOrganization.get());
            return new DataDto<>(organizationDto, HttpStatus.OK.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/organization/getById", HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<List<OrganizationDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }

    public DataDto<Boolean> block(Long id) {
        try {
            Optional<Organization> optionalOrganization = repository.findByIdAndDeletedFalse(id);
            Optional<AuthUser> optionalAuthUser = authUserRepository.findByAndOrganizationId(id);

            if (optionalOrganization.isPresent() && optionalAuthUser.isPresent()) {
                Organization organization = optionalOrganization.get();
                AuthUser authUser = optionalAuthUser.get();
                organization.setStatus(Status.BLOCKED);
                authUser.setStatus(Status.BLOCKED);
                authUserRepository.save(authUser);
                repository.save(organization);
                return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
            }
            return new DataDto<>(new AppErrorDto("Finding item not found  ", HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }

    }

    public DataDto<Boolean> unblock(Long id) {
        try {
            Optional<Organization> optionalOrganization = repository.findByIdAndDeletedFalse(id);
            Optional<AuthUser> optionalAuthUser = authUserRepository.findByAndOrganizationId(id);


            if (optionalOrganization.isPresent() && optionalAuthUser.isPresent()) {
                Organization organization = optionalOrganization.get();
                AuthUser authUser = optionalAuthUser.get();
                organization.setStatus(Status.ACTIVE);
                authUser.setStatus(Status.ACTIVE);
                authUserRepository.save(authUser);
                repository.save(organization);
                return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
            }
            return new DataDto<>(new AppErrorDto("Finding item not found  ", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    public DataDto<Boolean> deadline(Long id, Date date) {
        try {
            Optional<Organization> optionalOrganization = repository.findByIdAndDeletedFalse(id);
            if (optionalOrganization.isPresent()) {
                Organization organization = optionalOrganization.get();
                organization.setDeadline(date);
                repository.save(organization);
                return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
            }

            return new DataDto<>(new AppErrorDto("Finding item not found  ", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    public String getNameById(Long orgId) {
        return repository.getNameById(orgId);
    }

    public DataDto<List<OrganizationDto>> getAdminIdOrganizationAll(Long id) {
        Optional<AuthUser> optionalAuthUser = authUserRepository.getByIdAndNotDeleted(id);
        if (optionalAuthUser.isPresent()){
            AuthUser user = optionalAuthUser.get();
            if (user.getRole().equals(Role.ADMIN)){
                List<Organization> adminIdOrganizationAll = repository.findByOwnerId(id);
                List<OrganizationDto> organizationDtoList = mapper.toDto(adminIdOrganizationAll);
                return new DataDto<>(organizationDtoList, HttpStatus.OK.value());
            }
            return new DataDto<>(new AppErrorDto("The person in the admin role could not be found", HttpStatus.NOT_FOUND));
        }
        return new DataDto<>(new AppErrorDto("Finding item not found  ", HttpStatus.NOT_FOUND));
    }
}
