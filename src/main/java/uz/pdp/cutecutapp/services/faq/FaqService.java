package uz.pdp.cutecutapp.services.faq;

import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.faq.FaqCreateDto;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.faq.FaqUpdateDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.faq.FAQ;
import uz.pdp.cutecutapp.mapper.faq.FaqMapper;
import uz.pdp.cutecutapp.repository.faq.FaqRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.sql.SQLException;
import java.util.List;

@Service
public class FaqService extends AbstractService<FaqRepository, FaqMapper>
        implements GenericCrudService<FAQ, FaqDto, FaqCreateDto, FaqUpdateDto, BaseCriteria, Long> {
    public FaqService(FaqRepository repository, FaqMapper mapper, FaqRepository faqRepository) {
        super(repository, mapper);
        this.faqRepository = faqRepository;
    }

    private final FaqRepository faqRepository;
    @Override
    public DataDto<Long> create(FaqCreateDto createDto) {
        return null;
    }

    @Override
    public DataDto<Void> delete(Long id) {
        return null;
    }

    @Override
    public DataDto<Boolean> update(FaqUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<FaqDto>> getAll() {
        return (DataDto<List<FaqDto>>) faqRepository.findAll();
    }

    @Override
    public DataDto<FaqDto> get(Long id) {

        return null;
    }

    @Override
    public DataDto<List<FaqDto>> getWithCriteria(BaseCriteria criteria) throws SQLException {
        return null;
    }
}
