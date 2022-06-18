package com.bahadirmemis.mobileactionbootcamp.acc.dto;

import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountType;
import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccCurrencyType;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class AccAccountDto {

    private Long id;
    private Long cusCustomerId;
    private String ibanNo;
    private BigDecimal currentBalance;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType enumAccAccountType;
    private EnumGenStatus status;
    private Date cancelDate;
}
