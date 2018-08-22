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
@Table(name = "system_component", schema = "datafabric_dea")
public class SystemComponents  extends BaseEntity {
	SystemComponents(){
		
	}
	@Column(name = "systemid",nullable = true)
	private String systemId;
	
	@Column(name = "componentsdesc",nullable = true)
	private String componentsDesc;
	
	@Transient
	private Number relevancy;
}
