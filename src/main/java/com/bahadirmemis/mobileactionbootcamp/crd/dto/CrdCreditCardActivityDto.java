package com.bahadirmemis.mobileactionbootcamp.crd.dto;

import com.bahadirmemis.mobileactionbootcamp.crd.enums.EnumCrdCreditCardActivityType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CrdCreditCardActivityDto {

    private Long id;
    private Long crdCreditCardId;
    private BigDecimal amount;
    private Date transactionDate;
    private String description;
    private EnumCrdCreditCardActivityType creditCardActivityType;
}
