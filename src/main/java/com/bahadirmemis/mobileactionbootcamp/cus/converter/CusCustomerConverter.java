package com.bahadirmemis.mobileactionbootcamp.cus.converter;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
public class CusCustomerConverter {

    public CusCustomer convertToCusCustomer(CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomer cusCustomer = new CusCustomer();
        cusCustomer.setName(cusCustomerSaveRequestDto.getName());
        cusCustomer.setSurname(cusCustomerSaveRequestDto.getSurname());
        cusCustomer.setIdentityNo(cusCustomerSaveRequestDto.getIdentityNo());
        cusCustomer.setPassword(cusCustomerSaveRequestDto.getPassword());

        return cusCustomer;
    }

    public List<CusCustomerDto> convertToCusCustomerDtoList(List<CusCustomer> cusCustomerList){

        List<CusCustomerDto> cusCustomerDtoList = new ArrayList<>();
        for (CusCustomer cusCustomer : cusCustomerList) {
            CusCustomerDto cusCustomerDto = convertToCusCustomerDto(cusCustomer);
            cusCustomerDtoList.add(cusCustomerDto);
        }

        return cusCustomerDtoList;
    }

    public CusCustomerDto convertToCusCustomerDto(CusCustomer cusCustomer){

        CusCustomerDto cusCustomerDto = new CusCustomerDto();
        cusCustomerDto.setId(cusCustomer.getId());
        cusCustomerDto.setName(cusCustomer.getName());
        cusCustomerDto.setSurname(cusCustomer.getSurname());
        cusCustomerDto.setIdentityNo(cusCustomer.getIdentityNo());

        return cusCustomerDto;
    }
}
