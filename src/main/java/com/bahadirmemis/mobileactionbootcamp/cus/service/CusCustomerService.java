package com.bahadirmemis.mobileactionbootcamp.cus.service;

import com.bahadirmemis.mobileactionbootcamp.cus.converter.CusCustomerMapper;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CusCustomerService {

    private final CusCustomerEntityService cusCustomerEntityService;

    public List<CusCustomerDto> findAll() {

        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();

        List<CusCustomerDto> cusCustomerDtoList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto findById(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findById(id).orElseThrow();

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findById(id).orElseThrow();

        cusCustomerEntityService.delete(cusCustomer);
    }

    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {

        Long id = cusCustomerUpdateRequestDto.getId();

        boolean isExist = cusCustomerEntityService.existsById(id);
        if (!isExist){
            throw new RuntimeException("Customer does not exist with given id: " + id);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }
}
