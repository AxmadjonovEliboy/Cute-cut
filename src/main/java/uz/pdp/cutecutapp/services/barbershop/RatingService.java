package uz.pdp.cutecutapp.services.barbershop;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.rating.RatingCreateDto;
import uz.pdp.cutecutapp.dto.rating.RatingDto;
import uz.pdp.cutecutapp.dto.rating.RatingUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.barbershop.Rating;
import uz.pdp.cutecutapp.mapper.barbershop.RatingMapper;
import uz.pdp.cutecutapp.repository.barbershop.RatingRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;
import uz.pdp.cutecutapp.session.SessionUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService extends AbstractService<RatingRepository, RatingMapper>
        implements GenericCrudService<Rating, RatingDto, RatingCreateDto, RatingUpdateDto, BaseCriteria, Long> {

    private final SessionUser sessionUser;

    public RatingService(RatingRepository repository, RatingMapper mapper, SessionUser sessionUser) {
        super(repository, mapper);
        this.sessionUser = sessionUser;
    }

    /**
     * Creates new Rating for barberShop using SessionId and explicit param
     *
     * @param createDto -> explicit param which is Dto of rating
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */

    @Override
    public DataDto<Long> create(RatingCreateDto createDto) {
        Rating rating = mapper.fromCreateDto(createDto);
        rating.setClientId(sessionUser.getId());
        Rating newRating = repository.saveAndFlush(rating);
        return new DataDto<>(newRating.getId(), HttpStatus.CREATED.value());
    }

    /**
     * Soft Deletes  rating explicit param
     *
     * @param id -> explicit param which id Id of Rating
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */

    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            repository.softDelete(id);
            return new DataDto<>(null, HttpStatus.NO_CONTENT.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/rating/delete", HttpStatus.NOT_FOUND));
    }

    /**
     * Updates Rating using SessionId and explicit param
     *
     * @param updateDto -> explicit param which is Dto of rating
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */

    @Override
    public DataDto<Boolean> update(RatingUpdateDto updateDto) {
        try {
            Optional<Rating> optional = repository.findByIdAndDeletedFalse(updateDto.getId());
            Rating rating = mapper.fromUpdate(updateDto, optional.get());
            repository.save(rating);
            return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * finds All Ratings which are not deleted
     *
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */

    @Override
    public DataDto<List<RatingDto>> getAll() {
        return null;
    }

    /**
     * finds a Rating using explicit param which is not deleted
     *
     * @param id -> explicit param which is Id of Rating
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */


    @Override
    public DataDto<RatingDto> get(Long id) {
        Optional<Rating> rating = repository.findByIdAndDeletedFalse(id);
        if (rating.isPresent()) {
            RatingDto ratingDto = mapper.toDto(rating.get());
            return new DataDto<>(ratingDto, HttpStatus.OK.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/rating/getById", HttpStatus.NOT_FOUND));
    }

    /**
     * finds All Ratings using explicit param which are not deleted
     *
     * @param criteria -> explicit param which is criteria for filtering
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     * @see uz.pdp.cutecutapp.criteria.BaseCriteria
     */


    @Override
    public DataDto<List<RatingDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }

    /**
     * finds All Ratings using explicit param which are not deleted
     *
     * @param id -> explicit param which is Id of BarberShop
     * @return DataDto
     * @see uz.pdp.cutecutapp.dto.responce.DataDto
     */


    public DataDto<List<RatingDto>> getByBarberShopId(Long id) {
        List<Rating> ratings = repository.findByBarberShopIdAndDeletedFalse(id);
        List<RatingDto> ratingDtos = mapper.toDto(ratings);
        return new DataDto<>(ratingDtos, HttpStatus.OK.value());
    }
}
