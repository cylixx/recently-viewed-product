package com.market.product.viewed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ViewedProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewedProductServiceApplication.class, args);
	}

}
