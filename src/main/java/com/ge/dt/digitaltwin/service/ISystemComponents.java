/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.ResponseObj;
import com.ge.dt.digitaltwin.domain.SystemComponents;

public interface ISystemComponents extends GenericDao<SystemComponents, SystemComponents, Long> {

	List<SystemComponents> getSystemComponents(String systemId, String compDesc, Integer offset, Integer limit, boolean fetchAll);

	Long getFilteredCount(String systemId, String compDesc, Integer offset, Integer limit, boolean fetchAll);

	ResponseObj<SystemComponents> getSystemComponents(Map<String, String> reqParam);

}
