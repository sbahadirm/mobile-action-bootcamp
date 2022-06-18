package com.bahadirmemis.mobileactionbootcamp.cus.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class CusCustomerSaveRequestDto {

    @NotNull
    private String name;
    private String surname;
    private Long identityNo;

    @Size(min = 9)
    private String password;
}
