package com.bahadirmemis.mobileactionbootcamp.crd.entity;

import com.bahadirmemis.mobileactionbootcamp.crd.enums.EnumCrdCreditCardActivityType;
import com.bahadirmemis.mobileactionbootcamp.gen.entity.BaseEntity;
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
@Table(name = "CRD_CREDIT_CARD_ACTIVITY")
@Getter
@Setter
public class CrdCreditCardActivity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "CrdCreditCardActivity")
    @SequenceGenerator(name = "CrdCreditCardActivity", sequenceName = "CRD_CREDIT_CARD_ACTIVTY_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CRD_CREDIT_CARD")
    private CrdCreditCard crdCreditCard;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "CREDIT_CARD_ACTIVITY_TYPE", length = 30)
    private EnumCrdCreditCardActivityType creditCardActivityType;

}
