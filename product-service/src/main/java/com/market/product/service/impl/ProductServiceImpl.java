package com.market.product.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.product.message.repository.ProductRepository;
import com.market.product.model.Product;
import com.market.product.service.ProduceMessageService;
import com.market.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProduceMessageService produceMessageService;

	@Override
	public Optional<Product> findById(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product;
	}

	@Override
	public Optional<Product> findById(Long userId, Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			// push product message in a Queue
			produceMessageService.pushProducVisitedtMessage(userId, product);
		}

		return product;
	}

}
