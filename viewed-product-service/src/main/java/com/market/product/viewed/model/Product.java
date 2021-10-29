package com.market.product.viewed.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@ToString(exclude = {"productViewed"})
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

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductViewed> productViewed;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductMostViewed> productMostViewed;
}
