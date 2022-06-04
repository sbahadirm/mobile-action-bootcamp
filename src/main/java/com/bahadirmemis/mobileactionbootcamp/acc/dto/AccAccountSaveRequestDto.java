package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccCurrencyType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class AccAccountSaveRequestDto {

    private Long cusCustomerId;
    private BigDecimal currentBalance;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType enumAccAccountType;
}
