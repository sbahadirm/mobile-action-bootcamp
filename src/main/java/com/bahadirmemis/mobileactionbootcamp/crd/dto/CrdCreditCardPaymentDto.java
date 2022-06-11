package com.bahadirmemis.mobileactionbootcamp.crd.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CrdCreditCardPaymentDto {

    private Long crdCreditCardId;
    private BigDecimal amount;
}
