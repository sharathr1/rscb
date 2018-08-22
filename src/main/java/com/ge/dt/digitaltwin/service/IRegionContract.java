/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.RegionContract;
import com.ge.dt.digitaltwin.domain.ResponseObj;

public interface IRegionContract extends GenericDao<RegionContract, RegionContract, Long> {

	List<RegionContract> getRegionContract(String region, String prod, String contractName, Integer offset,
			Integer limit, boolean fetchAll);

	Long getFilteredCount(String region, String prod, String contractName, Integer offset,
			Integer limit, boolean fetchAll);

	ResponseObj<RegionContract>  getRegionContract(Map<String, String> reqParam);

	ResponseObj<RegionContract> getRegionContractFS(Map<String, String> reqParam);

}
