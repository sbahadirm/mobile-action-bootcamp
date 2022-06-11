package com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.crd.dao.CrdCreditCardDao;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CrdCreditCardEntityService extends BaseEntityService<CrdCreditCard, CrdCreditCardDao> {

    public CrdCreditCardEntityService(CrdCreditCardDao dao) {
        super(dao);
    }

    public CrdCreditCard findActiveByCardNoAndCvvNoAndExpireDate(Long cardNo, Long cvvNo, Date expireDate){
        return getDao().findByCardNoAndCvvNoAndExpireDateAndStatus(cardNo, cvvNo, expireDate, EnumGenStatus.ACTIVE);
    }
}
