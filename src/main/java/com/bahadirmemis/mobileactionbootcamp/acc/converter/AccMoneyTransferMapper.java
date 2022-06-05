package com.bahadirmemis.mobileactionbootcamp.acc.converter;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccMoneyTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferMapper {

    AccMoneyTransferMapper INSTANCE = Mappers.getMapper(AccMoneyTransferMapper.class);

    AccMoneyTransfer convertToAccMoneyTransfer(AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto);

    AccMoneyTransferDto convertToAccMoneyTransferDto(AccMoneyTransfer accMoneyTransfer);

//    @AfterMapping
//    default void setAccounts(@MappingTarget AccMoneyTransfer accMoneyTransfer, AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto){
//
//    }
}
