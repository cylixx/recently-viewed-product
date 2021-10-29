package com.market.product.service;

import java.util.Optional;

import com.market.product.model.Product;

public interface ProduceMessageService {
	
	void pushProducVisitedtMessage(Long customerId, Optional<Product> product); 

}
