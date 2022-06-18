package com.bahadirmemis.mobileactionbootcamp.cus.service;

import com.bahadirmemis.mobileactionbootcamp.cus.converter.CusCustomerMapper;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.enums.CusErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.GenErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public List<CusCustomerDto> findAll() {

        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();

        List<CusCustomerDto> cusCustomerDtoList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        if (cusCustomerSaveRequestDto == null){
            throw new GenBusinessException(GenErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        String password = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(password);
        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto findById(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        cusCustomerEntityService.delete(cusCustomer);
    }

    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {

        Long id = cusCustomerUpdateRequestDto.getId();

        boolean isExist = cusCustomerEntityService.existsById(id);
        if (!isExist){
            throw new GenBusinessException(CusErrorMessage.CUSTOMER_DOES_NOT_EXIST);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }
}
