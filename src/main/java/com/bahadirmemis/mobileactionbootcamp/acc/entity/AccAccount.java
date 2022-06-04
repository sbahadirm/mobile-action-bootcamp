package com.bahadirmemis.mobileactionbootcamp.acc.entity;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccCurrencyType;
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
@Table(name = "ACC_ACCOUNT")
@Getter
@Setter
public class AccAccount extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccount", sequenceName = "ACC_ACCOUNT_ID_SEQ")
    @GeneratedValue(generator = "AccAccount")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUS_CUSTOMER")
    private CusCustomer cusCustomer;

    @Column(name = "IBAN_NO", length = 30)
    private String ibanNo;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY_TYPE", length = 30)
    private EnumAccCurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", length = 30)
    private EnumAccAccountType enumAccAccountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30)
    private EnumGenStatus status;

    @Column(name = "CANCEL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;

}
