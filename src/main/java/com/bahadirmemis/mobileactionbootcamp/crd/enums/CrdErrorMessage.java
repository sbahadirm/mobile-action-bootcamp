package com.bahadirmemis.mobileactionbootcamp.crd.enums;

import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum CrdErrorMessage implements BaseErrorMessage {

    INVALID_CREDIT_CARD("Invalid credit card!"),
    INSUFFICIENT_LIMIT("Insufficient credit card limit!"),
    CREDIT_CARD_EXPIRED("Credit card expired!")
    ;

    private String message;

    CrdErrorMessage(String message) {
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
