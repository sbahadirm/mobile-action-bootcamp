package com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.crd.dao.CrdCreditCardDao;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDetails;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CrdCreditCardEntityService extends BaseEntityService<CrdCreditCard, CrdCreditCardDao> {

    public CrdCreditCardEntityService(CrdCreditCardDao dao) {
        super(dao);
    }

    public List<CrdCreditCard> findAllByStatusOrderById(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){
        PageRequest pageRequest = getPageRequest(pageOptional, sizeOptional);
        return getDao().findAllByStatusOrderById(EnumGenStatus.ACTIVE, pageRequest).toList();
    }

    public CrdCreditCard findActiveByCardNoAndCvvNoAndExpireDate(Long cardNo, Long cvvNo, Date expireDate){
        return getDao().findByCardNoAndCvvNoAndExpireDateAndStatus(cardNo, cvvNo, expireDate, EnumGenStatus.ACTIVE);
    }

    public CrdCreditCardDetails getCreditCardDetails(Long creditCardId){
        return getDao().getCreditCardDetails(creditCardId);
    }
}
