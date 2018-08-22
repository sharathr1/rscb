/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "customer_ib", schema = "datafabric_dea")
public class CustomerIB extends BaseEntity {
	public CustomerIB() {
	}

	@Column(name = "customer_name", nullable = true)
	private String customerName;

	@Column(name = "ib_count", nullable = true)
	private Integer ibCount;
	
	
	@Transient
	private Number relevancy;

}
