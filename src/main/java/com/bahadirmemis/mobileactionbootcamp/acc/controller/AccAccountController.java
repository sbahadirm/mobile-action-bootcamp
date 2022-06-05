package com.bahadirmemis.mobileactionbootcamp.acc.controller;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.*;
import com.bahadirmemis.mobileactionbootcamp.acc.service.AccAccountActivityService;
import com.bahadirmemis.mobileactionbootcamp.acc.service.AccAccountService;
import com.bahadirmemis.mobileactionbootcamp.acc.service.AccMoneyTransferService;
import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccAccountController {

    private final AccAccountService accAccountService;
    private final AccMoneyTransferService accMoneyTransferService;
    private final AccAccountActivityService accAccountActivityService;

    @Operation(tags = "Account Controller")
    @GetMapping
    public ResponseEntity findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){
        List<AccAccountDto> accAccountDtoList =  accAccountService.findAll(pageOptional, sizeOptional);

        return ResponseEntity.ok(RestResponse.of(accAccountDtoList));
    }

    @Operation(tags = "Account Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }

    @Operation(tags = "Account Controller")
    @PostMapping
    public ResponseEntity save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }

    @Operation(tags = "Account Controller")
    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id){

        accAccountService.cancel(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Account Activity Controller")
    @PostMapping("/money-transfer")
    public ResponseEntity transferMoney(@RequestBody AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto){

        AccMoneyTransferDto accMoneyTransferDto = accMoneyTransferService.transferMoney(accMoneyTransferSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyTransferDto));
    }

    @Operation(tags = "Account Activity Controller")
    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.withdraw(accMoneyActivityRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyActivityDto));
    }

    @Operation(tags = "Account Activity Controller")
    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.deposit(accMoneyActivityRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyActivityDto));
    }
}
