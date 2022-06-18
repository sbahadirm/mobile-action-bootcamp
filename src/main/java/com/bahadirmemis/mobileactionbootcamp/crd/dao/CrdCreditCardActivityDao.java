package com.bahadirmemis.mobileactionbootcamp.crd.dao;

import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CrdCreditCardActivityDao extends JpaRepository<CrdCreditCardActivity, Long> {

    List<CrdCreditCardActivity> findAllByCrdCreditCard_IdOrderById(Long crdCreditCardId);

    Page<CrdCreditCardActivity> findAllByCrdCreditCard_IdAndTransactionDateBetween(Long crdCreditCardId, Date startDate,
                                                                                   Date endDate, Pageable pageable);

}
