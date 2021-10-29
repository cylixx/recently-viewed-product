package com.market.product.viewed.service;

import java.util.List;
import java.util.Optional;

import com.market.product.viewed.dto.ProductViewedDto;
import com.market.product.viewed.model.ProductViewed;

public interface ProductViewedService {
	
	public List<ProductViewed> findAllProductViewed();
	public List<ProductViewedDto> findAllRecentlyViewedByCustomerId(Long customerId);
	public ProductViewed saveProductViewed(ProductViewed productViewed);
	public void updateProductViewed(Long customerId, Long productId);
	public void deleteProductViewed(Long customerId, Long productId);
	public Optional<ProductViewed> getProductViewedByCustomerIdAndProductId(Long customerId, Long productId);
	

}
