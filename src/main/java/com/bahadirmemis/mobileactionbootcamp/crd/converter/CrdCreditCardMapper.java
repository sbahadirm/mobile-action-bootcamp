package com.bahadirmemis.mobileactionbootcamp.crd.converter;

import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDto;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CrdCreditCardMapper {

    CrdCreditCardMapper INSTANCE = Mappers.getMapper(CrdCreditCardMapper.class);

    CrdCreditCardDto convertToCrdCreditCardDto(CrdCreditCard crdCreditCard);

    List<CrdCreditCardDto> convertToCrdCreditCardDtoList(List<CrdCreditCard> crdCreditCardList);

    CrdCreditCard convertToCrdCreditCard(CrdCreditCardSaveRequestDto crdCreditCardSaveRequestDto);
}

