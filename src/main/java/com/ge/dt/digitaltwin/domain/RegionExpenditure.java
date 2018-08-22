/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "region_expenditure", schema = "datafabric_dea")
public class RegionExpenditure extends BaseEntity {
	
	public RegionExpenditure() {
		super();
	}

	@Column(name = "region_description", nullable = true)
	private String regionDescription;

	@Column(name = "product_description", nullable = true)
	private String productDescription;

	@Column(name = "sr_process_year", nullable = true)
	private Integer srProcessYear;

	@Column(name = "sr_process_month", nullable = true)
	private Integer srProcessMonth;

	@Column(name = "material_spend", nullable = true)
	private BigDecimal materialSpend;

	@Column(name = "labor_spend", nullable = true)
	private BigDecimal laborSpend;

	@Column(name = "avg_labor_hours", nullable = true)
	private BigDecimal avgLaborHours;

	@Column(name = "total_ge_cost", nullable = true)
	private BigDecimal totalGeCost;

	@JsonIgnore
	public void getColumnName() {

	}
}
