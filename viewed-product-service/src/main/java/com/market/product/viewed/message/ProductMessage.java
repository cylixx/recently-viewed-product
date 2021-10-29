package com.market.product.viewed.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductMessage {
	private Long customerId;
	private Long productId;
	private String status; // visited
	private String message;

}
