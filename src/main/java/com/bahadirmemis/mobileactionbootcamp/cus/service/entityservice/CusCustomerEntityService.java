package com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.cus.dao.CusCustomerDao;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CusCustomerEntityService extends BaseEntityService<CusCustomer, CusCustomerDao> {

    public CusCustomerEntityService(CusCustomerDao dao) {
        super(dao);
    }

    public CusCustomer findByIdentityNo(Long identityNo){
        return getDao().findByIdentityNo(identityNo);
    }
}
