package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.mapper.barbershop.FavoritesMapper;
import uz.pdp.cutecutapp.repository.barbershop.FavoritesRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class FavoritesService extends AbstractService<FavoritesRepository, FavoritesMapper>
        implements GenericCrudService<Favorites, FavoritesDto, FavoritesCreateDto, GenericDto, BaseCriteria, Long> {
    public FavoritesService(FavoritesRepository repository, FavoritesMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(FavoritesCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Void> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(GenericDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<FavoritesDto>> getAll() {
        return null;
    }

    @Override
    public DataDto<FavoritesDto> get(Long id) {
        return null;
    }

    @Override
    public DataDto<List<FavoritesDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
