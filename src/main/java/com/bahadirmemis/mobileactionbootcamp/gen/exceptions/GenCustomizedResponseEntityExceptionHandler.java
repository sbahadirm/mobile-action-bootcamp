package com.bahadirmemis.mobileactionbootcamp.gen.exceptions;

import com.bahadirmemis.mobileactionbootcamp.gen.response.GenExceptionResponse;
import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import com.bahadirmemis.mobileactionbootcamp.jwt.service.AuthenticationService;
import com.bahadirmemis.mobileactionbootcamp.log.dto.LogErrorSaveDto;
import com.bahadirmemis.mobileactionbootcamp.log.entity.LogError;
import com.bahadirmemis.mobileactionbootcamp.log.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final LogService logService;

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleGenBusinessExceptions(GenBusinessException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, description, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = "Validation failed!";

        String description = "";
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        if (errorList != null && !errorList.isEmpty()){

            for (ObjectError objectError : errorList) {
                String defaultMessage = objectError.getDefaultMessage();

                description = description + defaultMessage + "\n";
            }
        } else {
            description = ex.getBindingResult().toString();
        }

        return getResponseEntity(errorDate, message, description, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String description, HttpStatus internalServerError) {
        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        ResponseEntity<RestResponse<GenExceptionResponse>> response = new ResponseEntity<>(restResponse, internalServerError);

        logError(response);

        return new ResponseEntity<>(restResponse, internalServerError);
    }

    private void logError(ResponseEntity<RestResponse<GenExceptionResponse>> response) {

        String errorMessageBody = getObjectString(response.getBody());
        String errorHeaders = getObjectString(response.getHeaders());

        LogErrorSaveDto logErrorSaveDto = LogErrorSaveDto.builder()
                .httpStatus(response.getStatusCode())
                .headers(errorHeaders)
                .body(errorMessageBody)
                .build();

        logService.log(logErrorSaveDto);
    }

    private String getObjectString(Object object) {
        String errorMessageBody = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            errorMessageBody = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return errorMessageBody;
    }


}
