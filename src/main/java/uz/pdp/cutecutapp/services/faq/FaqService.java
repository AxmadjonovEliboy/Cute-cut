package uz.pdp.cutecutapp.services.faq;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.criteria.BaseCriteria;
import uz.pdp.cutecutapp.dto.faq.FaqCreateDto;
import uz.pdp.cutecutapp.dto.faq.FaqDto;
import uz.pdp.cutecutapp.dto.faq.FaqUpdateDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.entity.faq.FAQ;
import uz.pdp.cutecutapp.mapper.faq.FaqMapper;
import uz.pdp.cutecutapp.repository.faq.FaqRepository;
import uz.pdp.cutecutapp.services.AbstractService;
import uz.pdp.cutecutapp.services.GenericCrudService;

import java.util.List;
import java.util.Optional;

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
        FAQ faq = mapper.fromCreateDto(createDto);
        FAQ newFaq = faqRepository.save(faq);
        return new DataDto<>(newFaq.getId(), HttpStatus.CREATED.value());
    }

    @Override
    public DataDto<Boolean> delete(Long id) {
        if (this.get(id).isSuccess()) {
            faqRepository.isDelete(id);
            return new DataDto<>(Boolean.TRUE, HttpStatus.NO_CONTENT.value());
        }
        return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/faq/delete", HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<Boolean> update(FaqUpdateDto updateDto) {
        try {
            Optional<FAQ> optionalFAQ = faqRepository.findByIdAndDeletedFalse(updateDto.getId());
            FAQ newFaq = mapper.fromUpdate(updateDto, optionalFAQ.get());
            repository.save(newFaq);
            return new DataDto<>(Boolean.TRUE, HttpStatus.OK.value());
        } catch (Exception e) {
            return new DataDto<>(Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public DataDto<List<FaqDto>> getAll() {
        List<FAQ> faqList = faqRepository.findAllByDeletedFalse();
        List<FaqDto> faqDtos = mapper.toDto(faqList);
        return new DataDto<>(faqDtos, HttpStatus.OK.value());
    }

    @Override
    public DataDto<FaqDto> get(Long id) {

        Optional<FAQ> faq = faqRepository.findByIdAndDeletedFalse(id);
        if (faq.isPresent()) {
            FaqDto faqDto = mapper.toDto(faq.get());
            return new DataDto<>(faqDto, HttpStatus.OK.value());
        } else
            return new DataDto<>(new AppErrorDto("Finding item not found with id : " + id, "/faq/getById", HttpStatus.NOT_FOUND));
    }

    @Override
    public DataDto<List<FaqDto>> getWithCriteria(BaseCriteria criteria) {
        return null;
    }


}
