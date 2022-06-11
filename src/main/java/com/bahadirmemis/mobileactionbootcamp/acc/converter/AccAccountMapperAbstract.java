package com.bahadirmemis.mobileactionbootcamp.acc.converter;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccAccountMapperAbstract {

    @Autowired
    CusCustomerEntityService cusCustomerEntityService;

    @Mapping(
            target = "cusCustomer",
            expression="java(cusCustomerEntityService.findByIdWithControl(accAccountSaveRequestDto.getCusCustomerId()))"
    )
    public abstract AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
