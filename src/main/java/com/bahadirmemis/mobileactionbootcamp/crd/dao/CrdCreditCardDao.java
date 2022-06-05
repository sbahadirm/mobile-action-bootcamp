package com.bahadirmemis.mobileactionbootcamp.crd.dao;

import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CrdCreditCardDao extends JpaRepository<CrdCreditCard, Long> {
}
