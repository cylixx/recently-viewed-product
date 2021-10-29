package com.market.product.viewed.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.product.viewed.model.ProductMostViewed;

@Repository
public interface ProductMostViewedRepository extends JpaRepository<ProductMostViewed, Long>{

	@Query("select pmv from ProductMostViewed pmv order by pmv.numberVisits desc")
	List<ProductMostViewed> findAllMostVisitedProducts(Pageable pageable);
	
}
