package com.bahadirmemis.mobileactionbootcamp.crd.entity;

import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.gen.entity.BaseEntity;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Entity
@Table(name = "CRD_CREDIT_CARD")
@Getter
@Setter
public class CrdCreditCard extends BaseEntity {

    @Id
    @GeneratedValue(generator = "CrdCreditCard")
    @SequenceGenerator(name = "CrdCreditCard", sequenceName = "CRD_CREDIT_CARD_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUS_CUSTOMER")
    private CusCustomer cusCustomer;

    @Column(name = "CARD_NO")
    private Long cardNo;

    @Column(name = "CVV_NO")
    private Long cvvNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;

    @Column(name = "TOTAL_LIMIT", precision = 19, scale = 2)
    private BigDecimal totalLimit;

    @Column(name = "AVAILABLE_CARD_LIMIT", precision = 19, scale = 2)
    private BigDecimal availableCardLimit;

    @Column(name = "CURRENT_DEBT", precision = 19, scale = 2)
    private BigDecimal currentDebt;

    @Column(name = "MINIMUM_PAYMENT_AMOUNT", precision = 19, scale = 2)
    private BigDecimal minimumPaymentAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "CUTOFF_DATE")
    private Date cutoffDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30)
    private EnumGenStatus status;

    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;
}
