package com.ge.dt.digitaltwin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ge.dt.digitaltwin.domain.ModalityCust.ModalityCustBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder

@AllArgsConstructor
@Table(name = "ModalityService", schema = "digitaltwin")
public class ModalityService extends BaseEntity {
	public ModalityService(){
		super();
	}

	@Column(name = "modality")
	private String modality;

	@Column(name = "region")
	private String region;

	@Column(name = "age")
	private String age;

	@Column(name = "system_coverage_level_warrantyc")
	private String systemCoverageLevelWarrantyc;

	@Column(name = "ib_count")
	private int ibCount;
}
