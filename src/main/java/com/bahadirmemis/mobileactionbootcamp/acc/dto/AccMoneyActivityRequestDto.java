package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccMoneyActivityRequestDto {

    private Long accAccountId;
    private BigDecimal amount;
}
