package com.bahadirmemis.mobileactionbootcamp.gen.util;

import com.bahadirmemis.mobileactionbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class StringUtil {

    public static Long getRandomNumber(int charCount){

        String randomNumeric;
        do {
            randomNumeric = getRandomNumberAsString(charCount);
        } while (randomNumeric.startsWith("0"));

        Long randomLong = null;
        if (StringUtils.hasText(randomNumeric)){
            randomLong = Long.parseLong(randomNumeric);
        }

        return randomLong;
    }

    public static String getRandomNumberAsString(int charCount){

        if (charCount <= 0){
            throw new GenBusinessException(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE);
        }

        String randomNumeric = RandomStringUtils.randomNumeric(charCount);

        return randomNumeric;
    }
}
