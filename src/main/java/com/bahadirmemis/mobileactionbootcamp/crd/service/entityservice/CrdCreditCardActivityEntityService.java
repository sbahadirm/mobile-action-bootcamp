package com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.crd.dao.CrdCreditCardActivityDao;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CrdCreditCardActivityEntityService extends BaseEntityService<CrdCreditCardActivity, CrdCreditCardActivityDao> {

    public CrdCreditCardActivityEntityService(CrdCreditCardActivityDao dao) {
        super(dao);
    }

    public List<CrdCreditCardActivity> findAllByCrdCreditCardId(Long crdCreditCardId){
        return getDao().findAllByCrdCreditCard_IdOrderById(crdCreditCardId);
    }
}
