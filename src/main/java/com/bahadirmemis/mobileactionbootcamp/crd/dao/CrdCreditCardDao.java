package com.bahadirmemis.mobileactionbootcamp.crd.dao;

import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CrdCreditCardDao extends JpaRepository<CrdCreditCard, Long> {

    CrdCreditCard findByCardNoAndCvvNoAndExpireDateAndStatus(Long cardNo, Long cvvNo, Date expireDate, EnumGenStatus status);
}
