package com.bahadirmemis.mobileactionbootcamp.crd.service;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.mobileactionbootcamp.crd.converter.CrdCreditCardMapper;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDto;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.crd.entity.CrdCreditCard;
import com.bahadirmemis.mobileactionbootcamp.crd.service.entityservice.CrdCreditCardEntityService;
import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import com.bahadirmemis.mobileactionbootcamp.gen.enums.EnumGenStatus;
import com.bahadirmemis.mobileactionbootcamp.gen.util.DateUtil;
import com.bahadirmemis.mobileactionbootcamp.gen.util.StringUtil;
import lombok.RequiredArgsConstructor;
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
    private final CusCustomerEntityService cusCustomerEntityService; // TODO: remove

    public List<CrdCreditCardDto> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<CrdCreditCard> crdCreditCardList = crdCreditCardEntityService.findAll(pageOptional, sizeOptional);

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

        Long cardNo = getCardNo();
        Long cvvNo = getCvvNo();

        BigDecimal limit = calculateLimit(crdCreditCardSaveRequestDto.getEarning());

        String cutoffDayStr = crdCreditCardSaveRequestDto.getCutoffDay();

        LocalDate cutoffDateLocal = calculateCutoffDateLocal(cutoffDayStr);
        Date cutoffDate = DateUtil.convertToDate(cutoffDateLocal);

        LocalDate dueDateLocal = cutoffDateLocal.plusDays(10L);
        Date dueDate = DateUtil.convertToDate(dueDateLocal);

        LocalDate expireDateLocal = LocalDate.now().plusYears(3L);
        Date expireDate = DateUtil.convertToDate(expireDateLocal);

        CrdCreditCard crdCreditCard = new CrdCreditCard();
        crdCreditCard.setCusCustomer(cusCustomer);
        crdCreditCard.setCardNo(cardNo);
        crdCreditCard.setCvvNo(cvvNo);
        crdCreditCard.setMinimumPaymentAmount(BigDecimal.ZERO);
        crdCreditCard.setCurrentDebt(BigDecimal.ZERO);
        crdCreditCard.setTotalLimit(limit);
        crdCreditCard.setCutoffDate(cutoffDate);
        crdCreditCard.setDueDate(dueDate);
        crdCreditCard.setExpireDate(expireDate);
        crdCreditCard.setAvailableCardLimit(limit);

        crdCreditCard = crdCreditCardEntityService.save(crdCreditCard);

        CrdCreditCardDto crdCreditCardDto = CrdCreditCardMapper.INSTANCE.convertToCrdCreditCardDto(crdCreditCard);

        return crdCreditCardDto;
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

    public void cancel(Long id) {

        CrdCreditCard crdCreditCard = crdCreditCardEntityService.findByIdWithControl(id);
        crdCreditCard.setStatus(EnumGenStatus.PASSIVE);
        crdCreditCard.setCancelDate(new Date());

        crdCreditCardEntityService.save(crdCreditCard);
    }

    private Long getCvvNo() {
        Long cvvNo = StringUtil.getRandomNumber(3);
        return cvvNo;
    }

    private Long getCardNo() {
        Long cardNo = StringUtil.getRandomNumber(16);
        return cardNo;
    }
}
