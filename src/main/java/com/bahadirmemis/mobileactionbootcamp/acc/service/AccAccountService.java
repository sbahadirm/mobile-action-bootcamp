package com.bahadirmemis.mobileactionbootcamp.acc.service;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import com.bahadirmemis.mobileactionbootcamp.acc.service.entityservice.AccAccountEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;

    public List<AccAccountDto> findAll() {

        List<AccAccount> accAccountList = accAccountEntityService.findAll();

        return null;
    }

    public List<AccAccountDto> findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional) {

        List<AccAccount> accAccountList = accAccountEntityService.findAll(pageOptional, sizeOptional);

        return null;
    }
}
