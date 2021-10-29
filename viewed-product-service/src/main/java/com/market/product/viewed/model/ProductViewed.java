package com.market.product.viewed.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

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
@ToString(exclude = { "customerId", "productId" })
@Entity
@Table(name = "PRODUCT_VIEWED")
public class ProductViewed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, insertable = true, updatable = true)
	@EqualsAndHashCode.Include
	private Long Id;

	@EqualsAndHashCode.Include
	@Column(name = "CUSTOMER_ID", nullable = false)
	private Long customerId;

	@EqualsAndHashCode.Include
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private Long productId;

	@Basic
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VIEWED", nullable = false, updatable = true, insertable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastViewed;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", insertable = false, updatable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
	private Product product;

}
