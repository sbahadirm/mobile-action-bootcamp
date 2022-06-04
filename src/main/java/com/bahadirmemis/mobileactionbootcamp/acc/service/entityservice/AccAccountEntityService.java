package com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.acc.dao.AccAccountDao;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
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
public class AccAccountEntityService {
    
    private final AccAccountDao accAccountDao;

    public List<AccAccount> findAll(){
        return accAccountDao.findAll();
    }

    public Optional<AccAccount> findById(Long id){
        return accAccountDao.findById(id);
    }

    public AccAccount save(AccAccount accAccount){
        return accAccountDao.save(accAccount);
    }

    public void delete(AccAccount accAccount){
        accAccountDao.delete(accAccount);
    }

    public boolean existsById(Long id){
        return accAccountDao.existsById(id);
    }
}
