package uz.pdp.cutecutapp.services.organization;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.organization.OrganizationCreateDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationDto;
import uz.pdp.cutecutapp.dto.organization.OrganizationUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.organization.Organization;
import uz.pdp.cutecutapp.mapper.organization.OrganizationMapper;
import uz.pdp.cutecutapp.repository.organization.OrganizationRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper>
        implements GenericCrudService<Organization, OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto, BaseCriteria, Long> {

    public OrganizationService(OrganizationRepository repository, OrganizationMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(OrganizationCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(OrganizationUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<OrganizationDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<OrganizationDto> get(Long id) {
        return null;
    }

    @Override
    public DataDto<List<OrganizationDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
