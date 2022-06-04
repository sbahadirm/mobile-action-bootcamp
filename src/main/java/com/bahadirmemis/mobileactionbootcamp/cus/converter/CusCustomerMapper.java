package com.bahadirmemis.mobileactionbootcamp.cus.converter;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CusCustomerMapper {

    CusCustomerMapper INSTANCE = Mappers.getMapper(CusCustomerMapper.class);

    CusCustomer convertToCusCustomer(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto);

    CusCustomerDto convertToCusCustomerDto(CusCustomer cusCustomer);

    List<CusCustomerDto> convertToCusCustomerDtoList(List<CusCustomer> cusCustomerList);

    CusCustomer convertToCusCustomer(CusCustomerSaveRequestDto cusCustomerSaveRequestDto);
}
