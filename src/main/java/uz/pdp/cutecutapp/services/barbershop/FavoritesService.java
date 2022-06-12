package uz.pdp.cutecutapp.services.barbershop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.GenericDto;
import uz.pdp.cutecutapp.dto.barbershop.BarberShopDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesCreateDto;
import uz.pdp.cutecutapp.dto.favorites.FavoritesDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.auth.AuthUser;
import uz.pdp.cutecutapp.entity.barbershop.BarberShop;
import uz.pdp.cutecutapp.entity.barbershop.Favorites;
import uz.pdp.cutecutapp.exception.NotFoundException;
import uz.pdp.cutecutapp.mapper.barbershop.BarberShopMapper;
import uz.pdp.cutecutapp.mapper.barbershop.FavoritesMapper;
import uz.pdp.cutecutapp.repository.auth.AuthUserRepository;
import uz.pdp.cutecutapp.repository.barbershop.BarberShopRepository;
import uz.pdp.cutecutapp.repository.barbershop.FavoritesRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FavoritesService extends AbstractService<FavoritesRepository, FavoritesMapper>
        implements GenericCrudService<Favorites, FavoritesDto, FavoritesCreateDto, GenericDto, BaseCriteria, Long> {
    public FavoritesService(FavoritesRepository repository, FavoritesMapper mapper, FavoritesRepository favoritesRepository, BarberShopRepository barberShopRepository, BarberShopMapper barberShopMapper, ObjectMapper objectMapper, AuthUserRepository authUserRepository) {
        super(repository, mapper);
        this.favoritesRepository = favoritesRepository;
        this.barberShopRepository = barberShopRepository;
        this.barberShopMapper = barberShopMapper;
        this.objectMapper = objectMapper;
        this.authUserRepository = authUserRepository;
    }

    private final FavoritesRepository favoritesRepository;
    private final BarberShopRepository barberShopRepository;
    private final BarberShopMapper barberShopMapper;
    private final ObjectMapper objectMapper;
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
    public DataDto<Boolean> delete(Long id) {
        Optional<Favorites> optionalFavorites = favoritesRepository.findById(id);
        if (optionalFavorites.isPresent()) {
            favoritesRepository.deleteById(id);
            return new DataDto<>(true);
        }
        throw new NotFoundException("Favourites not found");
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
        Optional<Favorites> optionalFavorites = favoritesRepository.findById(id);
        if (optionalFavorites.isPresent()) {
            Favorites favorites = optionalFavorites.get();
            return new DataDto<>(mapper.toDto(favorites));
        }
        throw new NotFoundException("Favourite not found");
    }

    @Override
    public DataDto<List<FavoritesDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }

    public List<BarberShopDto> getAllByUserId(Long id) {
        List<BarberShopDto> barberShopDtos = new ArrayList<>();
        try {
            List<BarberShop> barberShops = new ArrayList<>();
            String dataString = repository.getFavoritesByUserIdd(id);
            barberShops = objectMapper.readValue(dataString, new TypeReference<List<BarberShop>>() {
            });
            barberShopDtos = barberShopMapper.toDto(barberShops);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return barberShopDtos;
    }
}
