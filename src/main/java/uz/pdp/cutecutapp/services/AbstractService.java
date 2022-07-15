package uz.pdp.cutecutapp.services;

import uz.pdp.cutecutapp.mapper.GenericMapper;
import uz.pdp.cutecutapp.repository.BaseRepository;

/**
 * @param <R> Repository
 * @param <M> Mapper
 */
public abstract class AbstractService<R extends BaseRepository, M extends GenericMapper> implements BaseService {

    protected R repository;
    protected M mapper;


    public AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


}
