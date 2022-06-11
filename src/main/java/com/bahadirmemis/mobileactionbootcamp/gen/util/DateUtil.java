package com.bahadirmemis.mobileactionbootcamp.gen.util;

import com.bahadirmemis.mobileactionbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class DateUtil {

    public static Date convertToDate(LocalDate localDate){

        if (localDate == null){
            throw new GenBusinessException(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
        }

        Date date = Date.from(
                localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        return date;
    }
}
