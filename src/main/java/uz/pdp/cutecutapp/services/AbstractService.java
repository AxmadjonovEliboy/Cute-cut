package uz.pdp.cutecutapp.services;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cutecutapp.mapper.BaseMapper;

/**
 * @param <R> Repository
 * @param <M> Mapper
 */
public abstract class AbstractService<R extends JpaRepository, M extends BaseMapper> {

    protected R repository;
    protected M mapper;


    public AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


}
