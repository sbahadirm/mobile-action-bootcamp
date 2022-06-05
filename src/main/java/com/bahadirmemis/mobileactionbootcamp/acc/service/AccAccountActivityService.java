package com.bahadirmemis.mobileactionbootcamp.acc.service;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccMoneyActivityDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccountActivity;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccAccountActivityEntityService;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccAccountEntityService;
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
public class AccAccountActivityService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;

    public AccAccountActivity moneyOut(AccMoneyActivityDto accMoneyActivityDto) {

        validateMoneyActivityParameters(accMoneyActivityDto);

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityDto.getAccAccountId());

        BigDecimal newBalance = calculateAndValidateNewBalanceForMoneyOut(accMoneyActivityDto, accAccount);

        AccAccountActivity accAccountActivity = createAccAccountActivity(accMoneyActivityDto, accAccount, newBalance);

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;
    }

    public AccAccountActivity moneyIn(AccMoneyActivityDto accMoneyActivityDto) {

        validateMoneyActivityParameters(accMoneyActivityDto);

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityDto.getAccAccountId());

        BigDecimal newBalance = calculateNewBalanceForMoneyIn(accAccount, accMoneyActivityDto.getAmount());

        AccAccountActivity accAccountActivity = createAccAccountActivity(accMoneyActivityDto, accAccount, newBalance);

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;
    }

    private AccAccountActivity createAccAccountActivity(AccMoneyActivityDto accMoneyActivityDto, AccAccount accAccount, BigDecimal newBalance) {
        AccAccountActivity accAccountActivity = new AccAccountActivity();
        accAccountActivity.setAccountActivityType(accMoneyActivityDto.getAccountActivityType());
        accAccountActivity.setAmount(accMoneyActivityDto.getAmount());
        accAccountActivity.setAccAccount(accAccount);
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity.setTransactionDate(new Date());
        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);
        return accAccountActivity;
    }

    private void updateCurrentBalance(AccAccount accAccount, BigDecimal newBalance) {
        accAccount.setCurrentBalance(newBalance);
        accAccount = accAccountEntityService.save(accAccount);
    }

    private BigDecimal calculateAndValidateNewBalanceForMoneyOut(AccMoneyActivityDto accMoneyActivityDto, AccAccount accAccount) {
        BigDecimal newBalance = calculateNewBalanceForMoneyOut(accMoneyActivityDto, accAccount);

        validateBalance(newBalance);
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyOut(AccMoneyActivityDto accMoneyActivityDto, AccAccount accAccount) {
        BigDecimal newBalance = accAccount.getCurrentBalance().subtract(accMoneyActivityDto.getAmount());
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyIn(AccAccount accAccount, BigDecimal amount) {
        BigDecimal newBalance = accAccount.getCurrentBalance().add(amount);
        return newBalance;
    }

    private void validateMoneyActivityParameters(AccMoneyActivityDto accMoneyActivityDto) {
        if (accMoneyActivityDto == null) {
            throw new RuntimeException("Parameter cannot be null");
        }
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Insufficient balance!");
        }
    }
}
