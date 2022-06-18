package com.bahadirmemis.mobileactionbootcamp.gen.util;

import com.bahadirmemis.mobileactionbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
class StringUtilTest {

    @Test
    void shouldGetRandomNumber() {

        int charCount = 5;

        Long randomNumber = StringUtil.getRandomNumber(charCount);

        assertEquals(5, String.valueOf(randomNumber).length());
    }

    @Test
    void shouldNotGetRandomNumberWhenCharCountIsTooLong() {

        int charCount = 50;

        assertThrows(NumberFormatException.class, () -> StringUtil.getRandomNumber(charCount));

    }

    @Test
    void shouldGetRandomNumberAsString() {

        int charCount = 5;

        String randomNumber = StringUtil.getRandomNumberAsString(charCount);

        assertEquals(5, randomNumber.length());
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsZero() {

        int charCount = 0;

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> StringUtil.getRandomNumberAsString(charCount));

        assertEquals(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, genBusinessException.getBaseErrorMessage());
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsNegative() {

        int charCount = -1;

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> StringUtil.getRandomNumberAsString(charCount));

        assertEquals(GenErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, genBusinessException.getBaseErrorMessage());
    }
}