package com.ge.dt.digitaltwin.domain;
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
@Table(name = "ModalityCust", schema = "digitaltwin")
public class ModalityCust extends BaseEntity{
	public ModalityCust(){
		super();
	}

	@Column(name = "modality",nullable = true)
	private String modality;
	
	@Column(name = "customer_name",nullable = true)
	private String customerName;
	
	@Column(name = "ib_count",nullable = false, columnDefinition = "bigint(20) default 0")
	private Integer ibCount;

	@Column(name = "rnk",nullable = false, columnDefinition ="bigint(20) default 0")
	private Integer rnk;

	
}
