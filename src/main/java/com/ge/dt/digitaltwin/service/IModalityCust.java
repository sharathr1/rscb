/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.ModalityCust;
import com.ge.dt.digitaltwin.domain.ResponseObj;

public interface IModalityCust extends GenericDao<ModalityCust, ModalityCust, Long> {

	List<ModalityCust> getModality(String modality, String customerName, Integer offset, Integer limit, boolean fetchAll);

	Long getFilteredCount(String modality, String customerName, Integer offset, Integer limit, boolean fetchAll);

	ResponseObj<ModalityCust> getModality(Map<String, String> reqParam);



}
