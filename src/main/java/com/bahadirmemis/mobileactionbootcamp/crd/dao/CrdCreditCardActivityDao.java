package com.bahadirmemis.mobileactionbootcamp.crd.dao;

import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CrdCreditCardActivityDao extends JpaRepository<CrdCreditCardActivity, Long> {

    List<CrdCreditCardActivity> findAllByCrdCreditCard_IdOrderById(Long crdCreditCardId);
}
