package com.market.product.viewed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.product.viewed.dto.ProductViewedDto;
import com.market.product.viewed.model.ProductViewed;
import com.market.product.viewed.service.ProductViewedService;

@RestController
@RequestMapping("/api/product/viewed")
public class ProductViewedController {

	@Autowired
	private ProductViewedService productViewedService;

	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ProductViewed productViewed) {
		ProductViewed saved = null;

		try {
			saved = productViewedService.saveProductViewed(productViewed);

		} catch (DataIntegrityViolationException e) {
			if (e instanceof DataIntegrityViolationException) {
				return new ResponseEntity<Object>("Error: Duplicated product key", HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (saved == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("Product viewed " + saved.getId() + " created!", HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductViewed>> findAllProductViewed() {

		List<ProductViewed> list = productViewedService.findAllProductViewed();
		if (list.isEmpty()) {
			return new ResponseEntity<List<ProductViewed>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ProductViewed>>(list, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<List<ProductViewedDto>> findAllRecentlyProductViewed(@PathVariable long customerId) {
		List<ProductViewedDto> list = productViewedService.findAllRecentlyViewedByCustomerId(customerId);
		if (list.isEmpty()) {
			return new ResponseEntity<List<ProductViewedDto>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ProductViewedDto>>(list, HttpStatus.OK);
	}

	@PutMapping("/{customerId}/{productId}")
	public ResponseEntity<?> updateProductViewed(@PathVariable long customerId, @PathVariable long productId) {
		productViewedService.updateProductViewed(customerId, productId);

		return new ResponseEntity<Object>("Product viewed updated!", HttpStatus.OK);
	}

	@DeleteMapping("/{customerId}/{productId}")
	public ResponseEntity<?> deleteProductViewed(@PathVariable long customerId, @PathVariable long productId) {
		productViewedService.deleteProductViewed(customerId, productId);

		return new ResponseEntity<Object>("Product viewed deleted!", HttpStatus.OK);
	}

}
