package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountActivityType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class AccMoneyActivityDto {

    private Long accAccountId;
    private BigDecimal amount;
    private EnumAccAccountActivityType accountActivityType;
}
