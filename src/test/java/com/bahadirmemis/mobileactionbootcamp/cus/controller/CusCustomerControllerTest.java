package com.bahadirmemis.mobileactionbootcamp.cus.controller;

import com.bahadirmemis.mobileactionbootcamp.BaseTest;
import com.bahadirmemis.mobileactionbootcamp.MobileActionBootcampApplication;
import com.bahadirmemis.mobileactionbootcamp.config.H2TestProfileJPAConfig;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {MobileActionBootcampApplication.class, H2TestProfileJPAConfig.class})
class CusCustomerControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/customers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void save() throws Exception {

        String body = "{\n" +
                "  \"name\": \"ahmet\",\n" +
                "  \"surname\": \"kurt\",\n" +
                "  \"identityNo\": 12345678901,\n" +
                "  \"password\": \"Ahmet.1234\"\n" +
                "}";

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void save2() throws Exception {

        CusCustomerSaveRequestDto customerSaveRequestDto = CusCustomerSaveRequestDto.builder()
                .name("Ahmet")
                .surname("Kurt")
                .identityNo(12345678901L)
                .password("Ahmet.1234")
                .build();

        String body = objectMapper.writeValueAsString(customerSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findById() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteMethod() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/99").content("99").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    /**
     * {
     *   "id": 0,
     *   "name": "string",
     *   "surname": "string",
     *   "identityNo": 0,
     *   "password": "string"
     * }
     * @throws Exception
     */
    @Test
    void update() throws Exception {

        CusCustomerUpdateRequestDto customerUpdateRequestDto = CusCustomerUpdateRequestDto.builder()
                .id(1L)
                .name("Bahadir")
                .surname("Memiş")
                .identityNo(12345678987L)
                .password("Parola.1234")
                .build();

        String body = objectMapper.writeValueAsString(customerUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

}