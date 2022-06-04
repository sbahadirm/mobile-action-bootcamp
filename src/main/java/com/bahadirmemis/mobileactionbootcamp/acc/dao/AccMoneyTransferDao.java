package com.bahadirmemis.mobileactionbootcamp.acc.dao;

import com.bahadirmemis.mobileactionbootcamp.acc.entity.AccMoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface AccMoneyTransferDao extends JpaRepository<AccMoneyTransfer, Long> {
}
