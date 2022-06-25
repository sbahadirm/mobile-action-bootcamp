package com.bahadirmemis.mobileactionbootcamp.log.dao;

import com.bahadirmemis.mobileactionbootcamp.log.entity.LogError;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface LogErrorDao extends JpaRepository<LogError, Long> {
}
