package com.market.product.viewed.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.market.product.viewed.dto.ProductViewedDto;
import com.market.product.viewed.model.ProductMostViewed;
import com.market.product.viewed.model.ProductViewed;
import com.market.product.viewed.repository.ProductMostViewedRepository;
import com.market.product.viewed.repository.ProductViewedRepository;
import com.market.product.viewed.service.ProductViewedService;

@Service
public class ProductViewedServiceImpl implements ProductViewedService {

	private static final Logger logger = LoggerFactory.getLogger(ProductViewedServiceImpl.class);

	@Value("${application.product.viewed.max}")
	private int maxNumberOfProductViewed;

	@Autowired
	private ProductViewedRepository productViewedRepository;

	@Autowired
	private ProductMostViewedRepository mostViewedRepository;

	// Find all product viewed
	public List<ProductViewed> findAllProductViewed() {
		logger.info("*** Database - Retraiving all product viewed.");
		return productViewedRepository.findAll();
	}

	// Find all recently viewed products by customer Id
	@Cacheable("productsViewed")
	public List<ProductViewedDto> findAllRecentlyViewedByCustomerId(Long customerId) {
		logger.info("*** Database - Retraiving list of recently products viewed by customer Id: {}", customerId);

		List<ProductViewedDto> result = new ArrayList<ProductViewedDto>();
		List<ProductViewed> recentlyViewedList = productViewedRepository.findAllRecentlyViewedByCustomerId(customerId,
												 PageRequest.of(0, maxNumberOfProductViewed));

		if (recentlyViewedList == null || recentlyViewedList.isEmpty()) {
			List<ProductMostViewed> productMostViewed = mostViewedRepository
							.findAllMostVisitedProducts(PageRequest.of(0, maxNumberOfProductViewed));
			result = convertToDtoFromProductMostViewed(productMostViewed);

		} else {
			result = convertToDto(recentlyViewedList);
		}

		return result;
	}

	// Save product visited by customer Id
	@CacheEvict(value = "productsViewed", allEntries = true)
	public ProductViewed saveProductViewed(ProductViewed productViewed) {
		return productViewedRepository.save(productViewed);
	}

	// Update product viewed by customer Id and product Id
	@CacheEvict(value = "productsViewed", allEntries = true)
	public void updateProductViewed(Long customerId, Long productId) {
		logger.info("*** Database - Updating product viewed {} by customer Id {}", productId, customerId);
		productViewedRepository.updateProductViewedByCustomerId(customerId, productId);
	}

	// Delete product viewed
	@CacheEvict(value = "productsViewed", allEntries = true)
	public void deleteProductViewed(Long customerId, Long productId) {
		logger.info("*** Database - Deleting product viewed {} by customer Id {}", productId, customerId);
		productViewedRepository.deleteByCustomerIdAndProductId(customerId, productId);
	}

	public Optional<ProductViewed> getProductViewedByCustomerIdAndProductId(Long customerId, Long productId) {
		logger.info("*** Database - Retraiving product viewed {} by customer Id {}", productId, customerId);
		return productViewedRepository.findByCustomerIdAndProductId(customerId, productId);
	}

	private List<ProductViewedDto> convertToDto(List<ProductViewed> listProductViewed) {
		return listProductViewed.stream()
				.map(p -> new ProductViewedDto(p.getProduct().getId(), p.getProduct().getName(),
						p.getProduct().getQuantity(), p.getProduct().getPrice(), p.getLastViewed()))
				.collect(Collectors.toList());
	}

	private List<ProductViewedDto> convertToDtoFromProductMostViewed(List<ProductMostViewed> listProductViewed) {
		return listProductViewed.stream().map(p -> new ProductViewedDto(p.getProduct().getId(),
				p.getProduct().getName(), p.getProduct().getQuantity(), p.getProduct().getPrice(), null))
				.collect(Collectors.toList());
	}

}
