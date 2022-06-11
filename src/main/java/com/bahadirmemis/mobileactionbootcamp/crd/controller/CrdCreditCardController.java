package com.bahadirmemis.mobileactionbootcamp.crd.controller;

import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardActivityDto;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardDto;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.crd.dto.CrdCreditCardSpendDto;
import com.bahadirmemis.mobileactionbootcamp.crd.service.CrdCreditCardService;
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
}
