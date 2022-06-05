package com.bahadirmemis.mobileactionbootcamp.gen.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class GenExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;
}
