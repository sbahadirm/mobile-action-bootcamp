package com.bahadirmemis.mobileactionbootcamp.log.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class LogErrorSaveDto {

    private HttpStatus httpStatus;
    private String body;
    private String headers;
}
