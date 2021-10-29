package com.market.product.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID", nullable = false, insertable = true, updatable = true)
	private Long id;
	private String name;
	private int quantity;
	private double price;

}
