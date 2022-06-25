package com.bahadirmemis.mobileactionbootcamp.log.service;

import com.bahadirmemis.mobileactionbootcamp.jwt.service.AuthenticationService;
import com.bahadirmemis.mobileactionbootcamp.log.dto.LogErrorSaveDto;
import com.bahadirmemis.mobileactionbootcamp.log.entity.LogError;
import com.bahadirmemis.mobileactionbootcamp.log.service.entityservice.LogErrorEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final LogErrorEntityService logErrorEntityService;

    public LogError log(LogErrorSaveDto logErrorSaveDto){

        LogError logError = new LogError();
        logError.setCusCustomerId(AuthenticationService.currentCustomerId());
        logError.setErrorDate(new Date());
        logError.setHttpStatus(logErrorSaveDto.getHttpStatus());
        logError.setHeaders(logErrorSaveDto.getHeaders());
        logError.setBody(logErrorSaveDto.getBody());
        logError = logErrorEntityService.save(logError);

        log.error(logErrorSaveDto.getBody());

        return logError;
    }
}
