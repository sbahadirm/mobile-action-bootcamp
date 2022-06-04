package com.bahadirmemis.mobileactionbootcamp.acc.entity;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccMoneyTransferType;
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
@Table(name = "ACC_MONEY_TRANSFER")
@Getter
@Setter
public class AccMoneyTransfer extends BaseEntity {

    @Id
    @GeneratedValue(generator = "AccMoneyTransfer")
    @SequenceGenerator(name = "AccMoneyTransfer", sequenceName = "ACC_MONEY_TRANSFER_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACC_ACCOUNT_FROM")
    private AccAccount accAccountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACC_ACCOUNT_TO")
    private AccAccount accAccountTo;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSFER_DATE")
    private Date transferDate;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "MONEY_TRANSFER_TYPE", length = 30)
    private EnumAccMoneyTransferType moneyTransferType;
}
