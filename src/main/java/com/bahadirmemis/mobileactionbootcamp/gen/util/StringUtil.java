package com.bahadirmemis.mobileactionbootcamp.gen.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class StringUtil {

    public static String getRandomNumberAsString(int charCount){

        if (charCount <= 0){
            throw new RuntimeException("Char count cannot be zero or negative!");
        }

        String randomNumeric = RandomStringUtils.randomNumeric(charCount);

        return randomNumeric;
    }
}
