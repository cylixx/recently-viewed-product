package com.market.product.viewed.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(exclude = { "productId" })
@Entity
@Table(name = "PRODUCT_MOST_VIEWED")
public class ProductMostViewed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, insertable = true, updatable = true)
	@EqualsAndHashCode.Include
	private Long Id;
	
	private Long numberVisits;

	@EqualsAndHashCode.Include
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private Long productId;


	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
	private Product product;

}
