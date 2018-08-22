/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Getter
//@Setter
@Builder
@AllArgsConstructor
@Table(name = "ModalityService", schema = "datafabric_dea")
public class ModalityService extends BaseEntity {
	public ModalityService() {
		super();
	}

	@Column(name = "modality")
	private String modality;

	@Column(name = "region")
	private String region;

//	@Column(name = "age")
//	private String age;

	@Column(name = "system_coverage_level_warrantyc")
	private String systemCoverageLevelWarrantyc;

	@Column(name = "ib_count", nullable = true)
	private Integer ibCount;
	
	@Transient
	private BigDecimal age  ;
	
	@Column(name = "avg_age", nullable = true)
	private BigDecimal avgAge;

	/**
	 * NOT IN USE
	 * 
	 * @return
	 */
	@JsonIgnore
	public Predicate<ModalityService> getAgePredicate() {
		Predicate<ModalityService> ageP = (Predicate<ModalityService>) u -> u.getAvgAge()==avgAge;
		return ageP;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSystemCoverageLevelWarrantyc() {
		return systemCoverageLevelWarrantyc;
	}

	public void setSystemCoverageLevelWarrantyc(String systemCoverageLevelWarrantyc) {
		this.systemCoverageLevelWarrantyc = systemCoverageLevelWarrantyc;
	}

	public Integer getIbCount() {
		return ibCount;
	}

	public void setIbCount(Integer ibCount) {
		this.ibCount = ibCount;
	}

	public BigDecimal getAvgAge() {
		return avgAge;
	}

	public void setAvgAge(BigDecimal avgAge) {
		this.avgAge = avgAge;
		this.age=avgAge.setScale(0, RoundingMode.HALF_UP);
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

}
