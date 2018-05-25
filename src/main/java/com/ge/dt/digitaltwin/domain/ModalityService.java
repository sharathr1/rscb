package com.ge.dt.digitaltwin.domain;

import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "ModalityService", schema = "digitaltwin")
public class ModalityService extends BaseEntity {
	public ModalityService() {
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

	@Column(name = "ib_count", nullable = true)
	private Integer ibCount;

	/**
	 * NOT IN USE
	 * 
	 * @return
	 */
	public Predicate<ModalityService> getAgePredicate() {
		Predicate<ModalityService> ageP = (Predicate<ModalityService>) u -> u.getAge().equalsIgnoreCase(age);
		return ageP;
	}
}
