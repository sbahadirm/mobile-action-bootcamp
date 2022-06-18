package com.bahadirmemis.mobileactionbootcamp.crd.dao;

import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDetails;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CrdCreditCardDao extends JpaRepository<CrdCreditCard, Long> {

    Page<CrdCreditCard> findAllByStatusOrderById(EnumGenStatus status, Pageable pageable);

    CrdCreditCard findByCardNoAndCvvNoAndExpireDateAndStatus(Long cardNo, Long cvvNo, Date expireDate, EnumGenStatus status);

    @Query(
            "select  " +
                    " new com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDetails( " +
                    " cusCustomer.name,  " +
                    " cusCustomer.surname," +
                    " crdCreditCard.cardNo, " +
                    " crdCreditCard.expireDate, " +
                    " crdCreditCard.currentDebt, " +
                    " crdCreditCard.minimumPaymentAmount , " +
                    " crdCreditCard.cutoffDate , " +
                    " crdCreditCard.dueDate " +
                    " ) " +
                    " from CrdCreditCard crdCreditCard " +
                    " left join CusCustomer cusCustomer on crdCreditCard.cusCustomer = cusCustomer " +
                    " where crdCreditCard.id = :creditCardId "
    )
    CrdCreditCardDetails getCreditCardDetails(Long creditCardId);

}
