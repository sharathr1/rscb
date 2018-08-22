/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.ModalityService;
import com.ge.dt.digitaltwin.domain.ResponseObj;

public interface IModalityService extends GenericDao<ModalityService, ModalityService, Long> {

	List<ModalityService> getModality(String modality, Double age, String region, String sysCovLevWarrantyc, Integer offset, Integer limit, boolean fetchAll);

	Long getFilteredCount(String modality, Double age, String region, String sysCovLevWarrantyc, Integer offset,
			Integer limit, boolean fetchAll);

	ResponseObj<ModalityService> getModality(Map<String, String> reqParam);

}

