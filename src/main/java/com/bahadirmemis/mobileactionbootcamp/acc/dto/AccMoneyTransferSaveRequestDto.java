package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyTransferSaveRequestDto {

    private Long accAccountIdFrom;
    private Long accAccountIdTo;
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;
}
