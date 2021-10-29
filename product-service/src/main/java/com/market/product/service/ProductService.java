package com.market.product.service;

import java.util.Optional;

import com.market.product.model.Product;

public interface ProductService {
	
	Optional<Product> findById(Long productId);
	Optional<Product> findById(Long userId, Long productId);

}
