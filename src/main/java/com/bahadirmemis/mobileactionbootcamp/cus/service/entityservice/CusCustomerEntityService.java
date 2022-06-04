package com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.cus.dao.CusCustomerDao;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
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
public class CusCustomerEntityService {

    private final CusCustomerDao cusCustomerDao;

    public List<CusCustomer> findAll(){
        return cusCustomerDao.findAll();
    }

    public Optional<CusCustomer> findById(Long id){
        return cusCustomerDao.findById(id);
    }

    public CusCustomer save(CusCustomer cusCustomer){
        return cusCustomerDao.save(cusCustomer);
    }

    public void delete(CusCustomer cusCustomer){
        cusCustomerDao.delete(cusCustomer);
    }

    public boolean existsById(Long id){
        return cusCustomerDao.existsById(id);
    }
}
