package com.bahadirmemis.mobileactionbootcamp;

import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {

        RestResponse restResponse = getRestResponse(result);

        return restResponse.isSuccess();
    }

    protected RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse = objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
        return restResponse;
    }
}
