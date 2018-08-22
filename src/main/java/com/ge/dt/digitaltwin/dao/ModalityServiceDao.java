/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ge.dt.digitaltwin.domain.ModalityService;
@Repository
public interface ModalityServiceDao extends JpaRepository<ModalityService, Long>  {

	@Query("SELECT m FROM ModalityService m ")
	public List<ModalityService> findAllWithOffset();
}