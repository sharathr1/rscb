package com.ge.dt.digitaltwin.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "ModalityCust", schema = "digitaltwin")
public class ModalityCust extends BaseEntity{
	public ModalityCust(){
		super();
	}

	@Column(name = "modality")
	private String modality;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "ib_count")
	private int ibCount;

	@Column(name = "rnk")
	private int rnk;
}
