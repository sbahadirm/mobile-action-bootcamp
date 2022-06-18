package com.bahadirmemis.mobileactionbootcamp.crd.controller;

import com.bahadirmemis.mobileactionbootcamp.crd.dto.*;
import com.bahadirmemis.mobileactionbootcamp.crd.service.CrdCreditCardService;
import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/credit-cards")
@RequiredArgsConstructor
public class CrdCreditCardController {
    
    private final CrdCreditCardService crdCreditCardService;

    @Operation(tags = "Credit Card Controller")
    @GetMapping
    public ResponseEntity findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){
        List<CrdCreditCardDto> crdCreditCardDtoList =  crdCreditCardService.findAll(pageOptional, sizeOptional);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardDtoList));
    }

    @Operation(tags = "Credit Card Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        CrdCreditCardDto crdCreditCardDto = crdCreditCardService.findById(id);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardDto));
    }

    @Operation(tags = "Credit Card Controller")
    @PostMapping
    public ResponseEntity save(@RequestBody CrdCreditCardSaveRequestDto crdCreditCardSaveRequestDto){

        CrdCreditCardDto crdCreditCardDto = crdCreditCardService.save(crdCreditCardSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardDto));
    }

    @Operation(tags = "Credit Card Controller")
    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id){

        crdCreditCardService.cancel(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @PostMapping("/spend")
    public ResponseEntity spend(@RequestBody CrdCreditCardSpendDto crdCreditCardSpendDto){

        CrdCreditCardActivityDto crdCreditCardActivityDto = crdCreditCardService.spend(crdCreditCardSpendDto);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDto));
    }

    @PostMapping("/refund/{activityId}")
    public ResponseEntity refund(@PathVariable Long activityId){

        CrdCreditCardActivityDto crdCreditCardActivityDto = crdCreditCardService.refund(activityId);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDto));
    }

    @PostMapping("/payment")
    public ResponseEntity payment(@RequestBody CrdCreditCardPaymentDto crdCreditCardPaymentDto){

        CrdCreditCardActivityDto crdCreditCardActivityDto = crdCreditCardService.payment(crdCreditCardPaymentDto);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDto));
    }

    @GetMapping("/{id}/statement")
    public ResponseEntity statement(@PathVariable Long id){

        CrdCreditCardDetails crdCreditCardDetails = crdCreditCardService.statement(id);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardDetails));
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity findAllActivities(
            @PathVariable Long id,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            Optional<Integer> pageOptional,
            Optional<Integer> sizeOptional
            ){

        List<CrdCreditCardActivityDto> crdCreditCardActivityDtoList =  crdCreditCardService.findAllActivities(id, startDate, endDate, pageOptional, sizeOptional);

        return ResponseEntity.ok(RestResponse.of(crdCreditCardActivityDtoList));
    }
}
