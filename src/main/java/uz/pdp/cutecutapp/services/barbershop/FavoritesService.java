package uz.pdp.cutecutapp.services.barbershop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.mapper.barbershop.FavoritesMapper;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.repository.barbershop.FavoritesRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class FavoritesService extends AbstractService<FavoritesRepository, FavoritesMapper>
        implements GenericCrudService<Favorites, FavoritesDto, FavoritesCreateDto, GenericDto, BaseCriteria, Long> {
    public FavoritesService(FavoritesRepository repository, FavoritesMapper mapper, FavoritesRepository favoritesRepository, BarberShopRepository barberShopRepository, AuthUserRepository authUserRepository) {
        super(repository, mapper);
        this.favoritesRepository = favoritesRepository;
        this.barberShopRepository = barberShopRepository;
        this.authUserRepository = authUserRepository;
    }

    private final FavoritesRepository favoritesRepository;
    private final BarberShopRepository barberShopRepository;

    private final AuthUserRepository authUserRepository;

    @Override
    public DataDto<Long> create(FavoritesCreateDto createDto) {
        Favorites favorites = mapper.fromCreateDto(createDto);
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(createDto.barberShopId);
        if (optionalBarberShop.isPresent()) {
            Optional<AuthUser> optionalAuthUser = authUserRepository.findById(createDto.clientId);
            if (optionalAuthUser.isPresent()) {
                favorites.setClientId(createDto.clientId);
                favorites.setBarberShopId(createDto.barberShopId);
            }
        }
        favoritesRepository.save(favorites);
        return new DataDto<>(favorites.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        favoritesRepository.deleteById(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Boolean> update(GenericDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<FavoritesDto>> getAll() {
        List<Favorites> all = favoritesRepository.findAll();
        return new DataDto<>(mapper.toDto(all));
    }

    @Override
    public DataDto<FavoritesDto> get(Long id) {
        Optional<Favorites> optionalFavorites = favoritesRepository.findById(id);
        if (optionalFavorites.isPresent()) {
            Favorites favorites = optionalFavorites.get();
            return  new DataDto<>(mapper.toDto(favorites));
        }
        return new DataDto<>();
    }

    @Override
    public DataDto<List<FavoritesDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
