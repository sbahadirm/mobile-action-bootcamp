package com.bahadirmemis.mobileactionbootcamp.springcore;

import lombok.Builder;
import lombok.Data;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Data
@Builder
public class TestReponse {

    private Long id;
    private String data;
    private boolean isSuccess;
}
