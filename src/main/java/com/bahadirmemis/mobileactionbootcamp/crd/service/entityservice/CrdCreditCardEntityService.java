package com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.crd.dao.CrdCreditCardDao;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CrdCreditCardEntityService extends BaseEntityService<CrdCreditCard, CrdCreditCardDao> {

    public CrdCreditCardEntityService(CrdCreditCardDao dao) {
        super(dao);
    }
}
