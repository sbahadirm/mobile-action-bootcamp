package com.bahadirmemis.mobileactionbootcamp.acc.controller;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountDto;
import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.acc.service.AccAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity findAll(Optional<Integer> pageOptional, Optional<Integer> sizeOptional){
        List<AccAccountDto> accAccountDtoList =  accAccountService.findAll(pageOptional, sizeOptional);

        return ResponseEntity.ok(accAccountDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return ResponseEntity.ok(accAccountDto);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return new ResponseEntity(accAccountDto, HttpStatus.CREATED);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id){

        accAccountService.cancel(id);

        return ResponseEntity.ok().build();
    }
}
