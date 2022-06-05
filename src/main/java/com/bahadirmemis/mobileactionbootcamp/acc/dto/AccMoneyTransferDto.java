package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyTransferDto {

    private Long accAccountIdFrom;
    private Long cusCustomerIdFrom;
    private String cusCustomerNameFrom;
    private Long accAccountIdTo;
    private Long cusCustomerIdTo;
    private String cusCustomerNameTo;
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;
    private Date transferDate;
}
