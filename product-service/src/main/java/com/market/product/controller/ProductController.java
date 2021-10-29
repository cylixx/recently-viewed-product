package com.market.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.product.model.Product;
import com.market.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@GetMapping("/{customerId}/{productId}") 
	public ResponseEntity<Product> getProductById(@PathVariable Long customerId, @PathVariable Long productId) {
		
		Optional<Product> product = productService.findById(customerId, productId);
		if (!product.isPresent()) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
}
