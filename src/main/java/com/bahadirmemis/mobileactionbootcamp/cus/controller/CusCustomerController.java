package com.bahadirmemis.mobileactionbootcamp.cus.controller;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.service.CusCustomerService;
import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {

    private final CusCustomerService cusCustomerService;

    @Operation(
            tags = "Customer Controller",
            description = "Gets all customers"
    )
    @GetMapping
    public ResponseEntity findAll(){

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        return ResponseEntity.ok(RestResponse.of(cusCustomerDtoList));
    }

    @Operation(
            tags = "Customer Controller",
            description = "saves new customer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "creates new customer",
                    content = @Content(
                            mediaType = "application/json",
                                    schema= @Schema(
                                    implementation = CusCustomerSaveRequestDto.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Turkish Citizen",
                                            summary = "New Turkish Citizen Customer",
                                            value = "{\n" +
                                                    "  \"name\": \"ahmet\",\n" +
                                                    "  \"surname\": \"kurt\",\n" +
                                                    "  \"identityNo\": 12345678901,\n" +
                                                    "  \"password\": \"Ahmet.1234\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Foreign Citizen",
                                            summary = "New Foreign Citizen Customer",
                                            value = "{\n" +
                                                    "  \"name\": \"john\",\n" +
                                                    "  \"surname\": \"grant\",\n" +
                                                    "  \"identityNo\": 90345678901,\n" +
                                                    "  \"password\": \"John.1234\"\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    )
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @Operation(tags = "Customer Controller")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @Operation(tags = "Customer Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        cusCustomerService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Customer Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }
}
