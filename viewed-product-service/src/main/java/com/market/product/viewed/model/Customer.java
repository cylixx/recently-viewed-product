package com.market.product.viewed.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString(exclude = { "productViewed" })
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true)
	private Long Id;
	private String name;
	private String address;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<ProductViewed> productViewed;
}
