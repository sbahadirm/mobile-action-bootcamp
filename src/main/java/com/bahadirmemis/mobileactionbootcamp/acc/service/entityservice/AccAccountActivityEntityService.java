package com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.acc.dao.AccAccountActivityDao;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccountActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccAccountActivityEntityService {
    
    private final AccAccountActivityDao accAccountActivityDao;

    public List<AccAccountActivity> findAll(){
        return accAccountActivityDao.findAll();
    }

    public Optional<AccAccountActivity> findById(Long id){
        return accAccountActivityDao.findById(id);
    }

    public AccAccountActivity save(AccAccountActivity accAccountActivity){
        return accAccountActivityDao.save(accAccountActivity);
    }

    public void delete(AccAccountActivity accAccountActivity){
        accAccountActivityDao.delete(accAccountActivity);
    }

    public boolean existsById(Long id){
        return accAccountActivityDao.existsById(id);
    }
}
