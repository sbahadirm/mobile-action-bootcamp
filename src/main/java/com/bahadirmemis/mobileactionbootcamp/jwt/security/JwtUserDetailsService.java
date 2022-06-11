package com.bahadirmemis.mobileactionbootcamp.jwt.security;

import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
import com.bahadirmemis.mobileactionbootcamp.cus.service.entityservice.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo = Long.valueOf(username);

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdentityNo(identityNo);

        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadByUserId(Long id){

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        return JwtUserDetails.create(cusCustomer);
    }
}
