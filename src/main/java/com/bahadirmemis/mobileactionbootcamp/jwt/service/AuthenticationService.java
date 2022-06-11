package com.bahadirmemis.mobileactionbootcamp.jwt.service;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.mobileactionbootcamp.cus.service.CusCustomerService;
import com.bahadirmemis.mobileactionbootcamp.jwt.dto.JwtLoginRequestDto;
import com.bahadirmemis.mobileactionbootcamp.jwt.enums.JwtConstant;
import com.bahadirmemis.mobileactionbootcamp.jwt.security.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CusCustomerService cusCustomerService;
    private final JwtTokenGenerator jwtTokenGenerator;

    public CusCustomerDto register(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {
        return cusCustomerService.save(cusCustomerSaveRequestDto);
    }

    public String login(JwtLoginRequestDto jwtLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLoginRequestDto.getIdentityNo().toString(), jwtLoginRequestDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String fullToken = JwtConstant.BEARER.getConstant() + token;

        return fullToken;
    }
}
