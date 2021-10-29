package com.market.product.viewed.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.market.product.viewed.model.ProductViewed;

@Repository
public interface ProductViewedRepository extends JpaRepository<ProductViewed, Long>{
	
	Optional<ProductViewed> findByCustomerIdAndProductId(Long customerId, Long productId);
	
	@Query("select pv from ProductViewed pv where pv.customerId = :customerId order by pv.lastViewed desc")
	List<ProductViewed> findAllRecentlyViewedByCustomerId(@Param("customerId") Long customerId, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("update ProductViewed pv set pv.lastViewed = CURRENT_TIMESTAMP where pv.customerId = :customerId and pv.productId = :productId")
	void updateProductViewedByCustomerId(@Param("customerId") Long customerId, @Param("productId") Long productId);
	
	@Transactional
	void deleteByCustomerIdAndProductId(Long customerId, Long productId);
	
}
