package com.bahadirmemis.mobileactionbootcamp.acc.enums;

import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum AccErrorMessage implements BaseErrorMessage {

    CURRENCY_TYPES_CANNOT_BE_DIFFERENT("Currency types cannot be different!"),
    INSUFFICIENT_BALANCE("Insufficient balance!"),
    ;

    private String message;

    AccErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
