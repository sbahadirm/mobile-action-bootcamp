package com.bahadirmemis.mobileactionbootcamp.crd.service;

import com.bahadirmemis.mobileactionbootcamp.crd.converter.CrdCreditCardActivityMapper;
import com.bahadirmemis.mobileactionbootcamp.crd.converter.CrdCreditCardMapper;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.*;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCardActivity;
import com.bahadirmemis.mobileactionbootcamp.crd.enums.CrdErrorMessage;
import com.bahadirmemis.mobileactionbootcamp.crd.enums.EnumCrdCreditCardActivityType;
import com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice.CrdCreditCardActivityEntityService;
import com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice.CrdCreditCardEntityService;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import com.bahadirmemis.mobileactionbootcamp.gen.exceptions.GenBusinessException;
import com.bahadirmemis.mobileactionbootcamp.gen.util.DateUtil;
import com.bahadirmemis.mobileactionbootcamp.gen.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CrdCreditCardService {

    private final CrdCreditCardEntityService crdCreditCardEntityService;
    private final CrdCreditCardActivityEntityService crdCreditCardActivityEntityService;
    private final CusCustomerEntityService cusCustomerEntityService; // TODO: remove

    public List<CrdCreditCardDto> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<CrdCreditCard> crdCreditCardList = crdCreditCardEntityService.findAllByStatusOrderById(pageOptional, sizeOptional);

        List<CrdCreditCardDto> crdCreditCardDtoList = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardDtoList(crdCreditCardList);

        return crdCreditCardDtoList;
    }

    public CrdCreditCardDto findById(Long id) {

        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findByIdWithControl(id);

        CrdCreditCardDto crdCreditCardDto = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardDto(crdCreditCard);

        return crdCreditCardDto;
    }

    public CrdCreditCardDto save(CrdCreditCardSaveRequestDto crdCreditCardSaveRequestDto) {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(crdCreditCardSaveRequestDto.getCusCustomerId());

        String cutoffDayStr = crdCreditCardSaveRequestDto.getCutoffDay();
        BigDecimal earning = crdCreditCardSaveRequestDto.getEarning();

        LocalDate cutoffDateLocal = calculateCutoffDateLocal(cutoffDayStr);
        Date cutoffDate = DateUtil.convertToDate(cutoffDateLocal);

        Date dueDate = calculateDueDate(cutoffDateLocal);

        CrdCreditCard crdCreditCard = createCrdCreditCard(cusCustomer, earning, cutoffDate, dueDate);

        CrdCreditCardDto crdCreditCardDto = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardDto(crdCreditCard);

        return crdCreditCardDto;
    }

    private CrdCreditCard createCrdCreditCard(CusCustomer cusCustomer, BigDecimal earning, Date cutoffDate, Date dueDate) {

        Long cardNo = getCardNo();
        Long cvvNo = getCvvNo();
        Date expireDate = getExpireDate();

        BigDecimal limit = calculateLimit(earning);

        CrdCreditCard crdCreditCard = new CrdCreditCard();
        crdCreditCard.setCusCustomer(cusCustomer);
        crdCreditCard.setCardNo(cardNo);
        crdCreditCard.setCvvNo(cvvNo);
        crdCreditCard.setTotalLimit(limit);
        crdCreditCard.setCutoffDate(cutoffDate);
        crdCreditCard.setDueDate(dueDate);
        crdCreditCard.setExpireDate(expireDate);
        crdCreditCard.setAvailableCardLimit(limit);
        crdCreditCard.setMinimumPaymentAmount(BigDecimal.ZERO);
        crdCreditCard.setCurrentDebt(BigDecimal.ZERO);
        crdCreditCard.setStatus(EnumGenStatus.ACTIVE);

        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);
        return crdCreditCard;
    }

    private Date getExpireDate() {
        LocalDate expireDateLocal = LocalDate.now().plusYears(3L);
        Date expireDate = DateUtil.convertToDate(expireDateLocal);
        return expireDate;
    }

    private Date calculateDueDate(LocalDate cutoffDateLocal) {
        LocalDate dueDateLocal = cutoffDateLocal.plusDays(10L);
        return DateUtil.convertToDate(dueDateLocal);
    }

    public void cancel(Long id) {

        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findByIdWithControl(id);
        crdCreditCard.setStatus(EnumGenStatus.PASSIVE);
        crdCreditCard.setCancelDate(new Date());

        crdCreditCardEntityService.save(crdCreditCard);
    }

    public CrdCreditCardActivityDto spend(CrdCreditCardSpendDto crdCreditCardSpendDto) {


        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findActiveByCardNoAndCvvNoAndExpireDate
                (crdCreditCardSpendDto.getCardNo(), crdCreditCardSpendDto.getCvvNo(), crdCreditCardSpendDto.getExpireDate());

        validateCrditCard(crdCreditCard);

        BigDecimal amount = crdCreditCardSpendDto.getAmount();
        
        BigDecimal newAvailableCardLimit = calculateNewAvailableCreditCardLimit(amount, crdCreditCard.getAvailableCardLimit());
        BigDecimal newCurrentDebt = calculateNewCurrentDebt(amount, crdCreditCard.getCurrentDebt());

        validateCreditCardLimit(newAvailableCardLimit);

        crdCreditCard = updateCrdCreditCard(crdCreditCard, newAvailableCardLimit, newCurrentDebt);

        CrdCreditCardActivity crdCreditCardActivity = createCrdCreditCardActivityForSpend(amount, crdCreditCardSpendDto.getDescription(), crdCreditCard);

        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);

        return crdCreditCardActivityDto;
    }

    public CrdCreditCardActivityDto refund(Long activityId) {

        CrdCreditCardActivity crdCreditCardActivity = crdCreditCardActivityEntityService.findByIdWithControl(activityId);

        BigDecimal amount = crdCreditCardActivity.getAmount();

        CrdCreditCard crdCreditCard = addLimitToCreditCard(crdCreditCardActivity.getCrdCreditCard(), amount);

        crdCreditCardActivity = createCrdCreditCardActivityForRefund(crdCreditCardActivity.getDescription(), amount, crdCreditCard);

        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);

        return crdCreditCardActivityDto;

    }

    public CrdCreditCardActivityDto payment(CrdCreditCardPaymentDto crdCreditCardPaymentDto) {

        BigDecimal amount = crdCreditCardPaymentDto.getAmount();
        Long crdCreditCardId = crdCreditCardPaymentDto.getCrdCreditCardId();

        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findByIdWithControl(crdCreditCardId);

        crdCreditCard = addLimitToCreditCard(crdCreditCard, amount);

        CrdCreditCardActivity crdCreditCardActivity = createCrdCreditCardActivityForPayment(amount, crdCreditCard);

        CrdCreditCardActivityDto crdCreditCardActivityDto = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDto(crdCreditCardActivity);

        return crdCreditCardActivityDto;
    }

    public CrdCreditCardDetails statement(Long id) {

        CrdCreditCardDetails creditCardDetails = crdCreditCardEntityService.getCreditCardDetails(id);

        List<CrdCreditCardActivity> crdCreditCardActivityList = crdCreditCardActivityEntityService.findAllByCrdCreditCardId(id);

        List<CrdCreditCardActivityDto> crdCreditCardActivityDtoList = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDtoList(crdCreditCardActivityList);

        creditCardDetails.setCrdCreditCardActivityDtoList(crdCreditCardActivityDtoList);

        return creditCardDetails;

    }

    private CrdCreditCardActivity createCrdCreditCardActivityForPayment(BigDecimal amount, CrdCreditCard crdCreditCard) {
        CrdCreditCardActivity crdCreditCardActivity = new CrdCreditCardActivity();
        crdCreditCardActivity.setCrdCreditCard(crdCreditCard);
        crdCreditCardActivity.setAmount(amount);
        crdCreditCardActivity.setDescription("PAYMENT");
        crdCreditCardActivity.setTransactionDate(new Date());
        crdCreditCardActivity.setCreditCardActivityType(EnumCrdCreditCardActivityType.PAYMENT);
        crdCreditCardActivity = crdCreditCardActivityEntityService.save(crdCreditCardActivity);
        return crdCreditCardActivity;
    }

    private CrdCreditCardActivity createCrdCreditCardActivityForRefund(String oldDescription, BigDecimal amount, CrdCreditCard crdCreditCard) {
        String description = "REFUND -> " + oldDescription;
        CrdCreditCardActivity crdCreditCardActivityRefund = new CrdCreditCardActivity();
        crdCreditCardActivityRefund.setCrdCreditCard(crdCreditCard);
        crdCreditCardActivityRefund.setAmount(amount);
        crdCreditCardActivityRefund.setDescription(description);
        crdCreditCardActivityRefund.setTransactionDate(new Date());
        crdCreditCardActivityRefund.setCreditCardActivityType(EnumCrdCreditCardActivityType.REFUND);
        crdCreditCardActivityRefund = crdCreditCardActivityEntityService.save(crdCreditCardActivityRefund);
        return crdCreditCardActivityRefund;
    }

    private CrdCreditCard addLimitToCreditCard(CrdCreditCard crdCreditCard, BigDecimal amount) {

        BigDecimal newCurrentDebt = crdCreditCard.getCurrentDebt().subtract(amount);
        BigDecimal newAvailableCardLimit = crdCreditCard.getAvailableCardLimit().add(amount);

        crdCreditCard = updateCrdCreditCard(crdCreditCard, newAvailableCardLimit, newCurrentDebt);

        return crdCreditCard;
    }

    private LocalDate calculateCutoffDateLocal(String cutoffDayStr) {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        Month nextMonth = Month.of(currentMonth).plus(1);

        Integer cutoffDay = getCutoffDay(cutoffDayStr);

        LocalDate cutoffDateLocal = LocalDate.of(currentYear, nextMonth, cutoffDay);
        return cutoffDateLocal;
    }

    private Integer getCutoffDay(String cutoffDayStr) {
        if (!StringUtils.hasText(cutoffDayStr)){
            cutoffDayStr = "1";
        }

        Integer cutoffDay = Integer.valueOf(cutoffDayStr);
        return cutoffDay;
    }

    private BigDecimal calculateLimit(BigDecimal earning) {
        BigDecimal limit = earning.multiply(BigDecimal.valueOf(3));
        return limit;
    }

    private Long getCvvNo() {
        Long cvvNo = StringUtil.getRandomNumber(3);
        return cvvNo;
    }

    private Long getCardNo() {
        Long cardNo = StringUtil.getRandomNumber(16);
        return cardNo;
    }

    private CrdCreditCard updateCrdCreditCard(CrdCreditCard crdCreditCard, BigDecimal newAvailableCardLimit, BigDecimal newCurrentDebt) {
        crdCreditCard.setAvailableCardLimit(newAvailableCardLimit);
        crdCreditCard.setCurrentDebt(newCurrentDebt);
        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);
        return crdCreditCard;
    }

    private CrdCreditCardActivity createCrdCreditCardActivityForSpend(BigDecimal amount, String description, CrdCreditCard crdCreditCard) {
        CrdCreditCardActivity crdCreditCardActivity = new CrdCreditCardActivity();
        crdCreditCardActivity.setCrdCreditCard(crdCreditCard);
        crdCreditCardActivity.setAmount(amount);
        crdCreditCardActivity.setDescription(description);
        crdCreditCardActivity.setCreditCardActivityType(EnumCrdCreditCardActivityType.SPEND);
        crdCreditCardActivity.setTransactionDate(new Date());

        crdCreditCardActivity = crdCreditCardActivityEntityService.save(crdCreditCardActivity);
        return crdCreditCardActivity;
    }

    private BigDecimal calculateNewCurrentDebt(BigDecimal amount, BigDecimal currentDebt) {
        BigDecimal newCurrentDebt = currentDebt.add(amount);
        return newCurrentDebt;
    }

    private BigDecimal calculateNewAvailableCreditCardLimit(BigDecimal amount, BigDecimal availableCardLimit) {
        BigDecimal newAvailableCardLimit = availableCardLimit.subtract(amount);
        return newAvailableCardLimit;
    }

    private void validateCreditCardLimit(BigDecimal newAvailableCardLimit) {
        if (newAvailableCardLimit.compareTo(BigDecimal.ZERO) < 0){
            throw new GenBusinessException(CrdErrorMessage.INSUFFICIENT_LIMIT);
        }
    }

    private void validateCrditCard(CrdCreditCard crdCreditCard) {
        if (crdCreditCard == null){
            throw new GenBusinessException(CrdErrorMessage.INVALID_CREDIT_CARD);
        }

        if (crdCreditCard.getExpireDate().before(new Date())){
            throw new GenBusinessException(CrdErrorMessage.CREDIT_CARD_EXPIRED);
        }
    }

    public List<CrdCreditCardActivityDto> findAllActivities(Long id, Date startDate, Date endDate,
                                                            Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<CrdCreditCardActivity> crdCreditCardActivityList = crdCreditCardActivityEntityService.findAllByCrdCreditCardIdAndTransactionDateBetween(id, startDate, endDate,
                pageOptional, sizeOptional);

        List<CrdCreditCardActivityDto> result = CrdCreditCardActivityMapper.INSTANCE.convertToCrdCreditCardActivityDtoList(crdCreditCardActivityList);

        return result;

    }
}
