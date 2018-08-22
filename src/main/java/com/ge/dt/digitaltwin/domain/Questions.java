/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.domain;

import static javax.persistence.GenerationType.AUTO;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
@NoArgsConstructor
@Table(name = "questions", schema = "datafabric_dea")
public class Questions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id" , unique=true,nullable=false)
	private Long id;

	@Column(name = "user_id", nullable = true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long userId;

	@Column(name = "question", nullable = true)
	private String question;
	
	@Column(name = "time", nullable = true)
	private ZonedDateTime time = ZonedDateTime.now(ZoneId.systemDefault());

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	@JsonBackReference
	private UserProfile que;

	
}
