package com.market.product.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
