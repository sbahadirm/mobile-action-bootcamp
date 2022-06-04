package com.bahadirmemis.mobileactionbootcamp.acc.converter;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccAccountMapper {

    AccAccountMapper INSTANCE = Mappers.getMapper(AccAccountMapper.class);

    @Mapping(source = "cusCustomer.id", target = "cusCustomerId")
    AccAccountDto convertToAccAccountDto(AccAccount accAccount);

    List<AccAccountDto> convertToAccAccountDtoList(List<AccAccount> accAccountList);

    AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
