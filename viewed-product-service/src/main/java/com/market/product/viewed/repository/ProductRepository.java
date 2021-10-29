package com.market.product.viewed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.product.viewed.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
