package com.bahadirmemis.mobileactionbootcamp.springcore;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
