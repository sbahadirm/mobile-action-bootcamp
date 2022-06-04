package com.bahadirmemis.mobileactionbootcamp.acc.dao;

import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface AccAccountDao extends JpaRepository<AccAccount, Long> {
}
