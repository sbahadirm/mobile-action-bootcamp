package com.bahadirmemis.mobileactionbootcamp.acc.entity;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountActivityType;
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
@Table(name = "ACC_ACCOUNT_ACTIVITY")
@Getter
@Setter
public class AccAccountActivity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "AccAccountActivity")
    @SequenceGenerator(name = "AccAccountActivity", sequenceName = "ACC_ACCOUNT_ACTIVITY_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACC_ACCOUNT")
    private AccAccount accAccount;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_ACTIVITY_TYPE", length = 30)
    private EnumAccAccountActivityType accountActivityType;
}
