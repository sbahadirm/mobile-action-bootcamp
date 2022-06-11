package com.bahadirmemis.mobileactionbootcamp.jwt.controller;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.gen.response.RestResponse;
import com.bahadirmemis.mobileactionbootcamp.jwt.dto.JwtLoginRequestDto;
import com.bahadirmemis.mobileactionbootcamp.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity save(@Valid @RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = authenticationService.register(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtLoginRequestDto jwtLoginRequestDto){

        String login = authenticationService.login(jwtLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(login));
    }
}
