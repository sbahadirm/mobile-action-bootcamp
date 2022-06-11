package com.bahadirmemis.mobileactionbootcamp.crd.converter;

import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardActivityDto;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
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
public interface CrdCreditCardActivityMapper {

    CrdCreditCardActivityMapper INSTANCE = Mappers.getMapper(CrdCreditCardActivityMapper.class);

    @Mapping(target = "crdCreditCardId", source = "crdCreditCard.id")
    CrdCreditCardActivityDto convertToCrdCreditCardActivityDto(CrdCreditCardActivity crdCreditCardActivity);

    List<CrdCreditCardActivityDto> convertToCrdCreditCardActivityDtoList(List<CrdCreditCardActivity> crdCreditCardActivityList);
}
