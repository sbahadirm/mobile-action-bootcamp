package com.bahadirmemis.mobileactionbootcamp.acc.service;

import com.bahadirmemis.mobileactionbootcamp.acc.converter.AccMoneyTransferMapper;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyTransferSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccMoneyTransfer;
import com.bahadirmemis.mobileactionbootcamp.acc.enums.AccErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.acc.enums.EnumAccAccountActivityType;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccAccountEntityService;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccMoneyTransferEntityService;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccMoneyTransferService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountActivityService accAccountActivityService;

    public AccMoneyTransferDto transferMoney(AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto) {

        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferSaveRequestDto);

        BigDecimal amount = accMoneyTransferSaveRequestDto.getAmount();
        Long accAccountIdFrom = accMoneyTransferSaveRequestDto.getAccAccountIdFrom();
        Long accAccountIdTo = accMoneyTransferSaveRequestDto.getAccAccountIdTo();

        AccAccount accAccountFrom = accAccountEntityService.findByIdWithControl(accAccountIdFrom);
        AccAccount accAccountTo = accAccountEntityService.findByIdWithControl(accAccountIdTo);

        validateCurrencyType(accAccountFrom, accAccountTo);

        accMoneyTransfer.setAccAccountFrom(accAccountFrom);
        accMoneyTransfer.setAccAccountTo(accAccountTo);
        accMoneyTransfer.setTransferDate(new Date());

        AccMoneyActivityDto accMoneyActivityDtoOut = AccMoneyActivityDto.builder()
                .accAccountId(accAccountIdFrom)
                .amount(amount)
                .accountActivityType(EnumAccAccountActivityType.SEND)
                .build();

        AccMoneyActivityDto accMoneyActivityDtoIn = AccMoneyActivityDto.builder()
                .accAccountId(accAccountIdTo)
                .amount(amount)
                .accountActivityType(EnumAccAccountActivityType.GET)
                .build();

        accAccountActivityService.moneyOut(accMoneyActivityDtoOut);
        accAccountActivityService.moneyIn(accMoneyActivityDtoIn);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);

        AccMoneyTransferDto accMoneyTransferDto = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransferDto(accMoneyTransfer);

        return accMoneyTransferDto;
    }

    private void validateCurrencyType(AccAccount accAccountFrom, AccAccount accAccountTo) {
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new GenBusinessException(AccErrorMessage.CURRENCY_TYPES_CANNOT_BE_DIFFERENT);
        }
    }
}
