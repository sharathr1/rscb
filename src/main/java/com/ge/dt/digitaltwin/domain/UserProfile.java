/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ge.dt.digitaltwin.domain.SystemComponents.SystemComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "user_profile", schema = "datafabric_dea")
public class UserProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserProfile() {

	}

	@Id
	@Column(name = "user_id", nullable = true)
	private Long userId;

	@Column(name = "sso_id", nullable = true)
	private Long ssoId;

	@Column(name = "user_name", nullable = true)
	private String userName;

	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "que")
	private List<Questions> questions = new ArrayList<>();

}
