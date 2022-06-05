package com.bahadirmemis.mobileactionbootcamp.acc.converter;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccMoneyTransfer;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccAccountEntityService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferMapper {

    AccMoneyTransferMapper INSTANCE = Mappers.getMapper(AccMoneyTransferMapper.class);

    AccMoneyTransfer convertToAccMoneyTransfer(AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto);

    @Mapping(target = "accAccountIdFrom", source = "accAccountFrom.id")
    @Mapping(target = "cusCustomerIdFrom", source = "accAccountFrom.cusCustomer.id")
    @Mapping(target = "cusCustomerNameFrom", source = "accAccountFrom.cusCustomer.name")
    @Mapping(target = "accAccountIdTo", source = "accAccountTo.id")
    @Mapping(target = "cusCustomerIdTo", source = "accAccountTo.cusCustomer.id")
    @Mapping(target = "cusCustomerNameTo", source = "accAccountTo.cusCustomer.name")
    AccMoneyTransferDto convertToAccMoneyTransferDto(AccMoneyTransfer accMoneyTransfer);

//    @AfterMapping
//    default void setAccounts(@MappingTarget AccMoneyTransfer accMoneyTransfer, AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto){
//
//        if (accMoneyTransferSaveRequestDto != null){
//
//        }
//
//    }
}
