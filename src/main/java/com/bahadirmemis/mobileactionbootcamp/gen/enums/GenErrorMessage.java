package com.bahadirmemis.mobileactionbootcamp.gen.enums;

import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.BaseErrorMessage;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum GenErrorMessage implements BaseErrorMessage {

    ITEM_NOT_FOUND("Item not found!"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null"),
    CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE("Char count cannot be zero or negative!"),
    ;

    private String message;

    GenErrorMessage(String message) {
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
