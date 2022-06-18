package com.bahadirmemis.mobileactionbootcamp.gen.util;

import com.bahadirmemis.mobileactionbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
class DateUtilTest {

    private static SimpleDateFormat formatterDate;
    private static SimpleDateFormat formatterDateTime;

    @BeforeAll
    public static void setup(){
        formatterDate = new SimpleDateFormat("dd-MM-yyyy");
        formatterDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    }

    @Test
    void shouldConvertToDate() {

        LocalDate localDate = LocalDate.of(2022, 01, 01);

        Date date = DateUtil.convertToDate(localDate);

        String format = formatterDate.format(date);

        assertEquals("01-01-2022", format);
    }

    @Test
    void shouldNotConvertToDateWhenParameterIsNull() {

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, () -> DateUtil.convertToDate(null));

        assertEquals(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED, genBusinessException.getBaseErrorMessage());
    }
}