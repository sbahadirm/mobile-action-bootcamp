package com.bahadirmemis.mobileactionbootcamp.cus.dto;

import lombok.Data;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
public class CusCustomerSaveRequestDto {

    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
