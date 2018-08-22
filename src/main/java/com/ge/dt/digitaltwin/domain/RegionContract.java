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
@Table(name = "region_contract", schema = "datafabric_dea")
public class RegionContract  extends BaseEntity{
	public RegionContract() {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "region",nullable = true)
	private String region;
	@Column(name = "product",nullable = true)
	private String product;
	@Column(name = "contract_name",nullable = true)
	private String contractOfferingName;
	@Transient
	private Number relevancy;
}
