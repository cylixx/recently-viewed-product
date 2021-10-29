package com.market.product.viewed.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewedDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long productId;
	private String name;
	private int quantity;
	private double price;
	private Date lastViewed;
}
