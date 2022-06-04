package com.bahadirmemis.mobileactionbootcamp.gen.service;

import com.bahadirmemis.mobileactionbootcamp.gen.entity.BaseAdditionalFields;
import com.bahadirmemis.mobileactionbootcamp.gen.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E,Long>> {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_SIZE = 10;

    private final D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public List<E> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        PageRequest pageRequest = getPageRequest(pageOptional, sizeOptional);

        return findAll(pageRequest);
    }

    public List<E> findAll(Pageable pageable){

        Page<E> page = dao.findAll(pageable);

        return page.toList();
    }

    public Optional<E> findById(Long id){
        return dao.findById(id);
    }

    public E findByIdWithControl(Long id){

        Optional<E> optionalEntity = dao.findById(id);

        E entity;
        if (optionalEntity.isPresent()){
            entity = optionalEntity.get();
        } else {
            throw new RuntimeException("Item not found");
        }

        return entity;
    }

    public E save(E entity){

        setAdditionalFields(entity);

        return dao.save(entity);
    }

    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
//            baseAdditionalFields.setCreatedBy(); // TODO: update here after jwt
        }

        baseAdditionalFields.setUpdateDate(new Date());
//        baseAdditionalFields.setUpdatedBy();// TODO: update here after jwt
    }

    public void delete(E e){
        dao.delete(e);
    }

    public boolean existsById(Long id){
        return dao.existsById(id);
    }

    protected PageRequest getPageRequest(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){

        Integer page = getPage(pageOptional);
        Integer size = getSize(pageOptional, sizeOptional);

        PageRequest pageRequest = PageRequest.of(page, size);

        return pageRequest;
    }

    protected Integer getSize(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {
        Integer size = DEFAULT_SIZE;
        if (pageOptional.isPresent()){
            size = sizeOptional.get();
        }
        return size;
    }

    protected Integer getPage(Optional<Integer> pageOptional) {
        Integer page = DEFAULT_PAGE;
        if (pageOptional.isPresent()){
            page = pageOptional.get();
        }
        return page;
    }
}
