/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;

import com.ge.dt.digitaltwin.domain.RegionExpenditure;

public interface IRegionExpenditure extends GenericDao<RegionExpenditure, RegionExpenditure, Long> {

	List<RegionExpenditure> getRegionExpenditure(String region, String prodDesc, Integer year, Integer mom, String periodTo, String periodFrom, Integer offset, Integer limit, boolean fetchAll);

	Long getFilteredCount(String region, String prodDesc, Integer year, Integer mon, String periodTo, String periodFrom, Integer offset, Integer limit,
			boolean fetchAll);

}
