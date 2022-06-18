package com.bahadirmemis.mobileactionbootcamp.cus.service;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class CusCustomerServiceTest {

    @Mock
    private CusCustomerEntityService cusCustomerEntityService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CusCustomerService cusCustomerService;

    @Test
    void shouldFindAll() {

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(0, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnCustomers() {

        CusCustomer cusCustomer = mock(CusCustomer.class);
        List<CusCustomer> cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertEquals(1, cusCustomerDtoList.size());
    }

    @Test
    void shouldFindAllWhenCustomerListIsNull() {

        when(cusCustomerEntityService.findAll()).thenReturn(null);

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        assertNull(cusCustomerDtoList);
    }

    @Test
    void shouldSave() {

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = mock(CusCustomerSaveRequestDto.class);
        when(cusCustomerSaveRequestDto.getPassword()).thenReturn("aaa");

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(passwordEncoder.encode("aaa")).thenReturn("12!453a56565H683GSD!sd");
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto result = cusCustomerService.save(cusCustomerSaveRequestDto);

        assertEquals(1L, result.getId());
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}