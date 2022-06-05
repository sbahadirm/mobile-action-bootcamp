package com.bahadirmemis.mobileactionbootcamp.acc.converter;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccountActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferActivityMapper {

    AccMoneyTransferActivityMapper INSTANCE = Mappers.getMapper(AccMoneyTransferActivityMapper.class);

    @Mapping(target = "accAccountId", source = "accAccount.id")
    AccMoneyActivityDto convertToAccMoneyActivityDto(AccAccountActivity accAccountActivity);

}
