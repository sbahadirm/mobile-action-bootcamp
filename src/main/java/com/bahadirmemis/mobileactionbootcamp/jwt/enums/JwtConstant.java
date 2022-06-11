package com.bahadirmemis.mobileactionbootcamp.jwt.enums;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public enum JwtConstant {

    BEARER("Bearer ")
    ;

    private String constant;

    JwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
