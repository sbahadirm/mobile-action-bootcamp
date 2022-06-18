package com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice;

import com.bahadirmemis.mobileactionbootcamp.crd.dao.CrdCreditCardActivityDao;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
import com.bahadirmemis.mobileactionbootcamp.gen.service.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<CrdCreditCardActivity> findAllByCrdCreditCardIdAndTransactionDateBetween(Long crdCreditCardId,
                                                                                         Date startDate, Date endDate,
                                                                                         Optional<Integer> pageOptional,
                                                                                         Optional<Integer> sizeOptional){

        PageRequest pageRequest = getPageRequest(pageOptional, sizeOptional);

        return getDao().findAllByCrdCreditCard_IdAndTransactionDateBetween(crdCreditCardId, startDate, endDate, pageRequest).toList();
    }
}
