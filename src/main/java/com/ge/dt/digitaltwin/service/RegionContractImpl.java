/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.dao.CustomTemplate;
import com.ge.dt.digitaltwin.domain.RegionContract;
import com.ge.dt.digitaltwin.domain.ResponseObj;

@Service
public class RegionContractImpl extends GenericDaoImpl<RegionContract, RegionContract, Long>
		implements IRegionContract {
	@Autowired
	private CustomTemplate custTemplate;

	@Override
	public List<RegionContract> getRegionContract(String region, String prod, String contractName, Integer offset,
			Integer limit, boolean fetchAll) {
		if (fetchAll == false) {
			List<Predicate<RegionContract>> allpredicates = getAllRequiredPredicate(region, prod, contractName);
			if (allpredicates.size() > 0) {
				Predicate<RegionContract> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_contract",
						getAllSQLFilter(region, prod, contractName), offset, limit)).stream().filter(requiredFilter)
								.collect(Collectors.toList());
			} else {
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_contract",
						getAllSQLFilter(region, prod, contractName), offset, limit));
			}
		} else {
			return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_contract",
					getAllSQLFilter(region, prod, contractName), offset, limit));
		}
	}

	@Override
	public Long getFilteredCount(String region, String prod, String contractName, Integer offset, Integer limit,
			boolean fetchAll) {
		String sql = custTemplate
				.sqlBuilder("datafabric_dea.region_contract", getAllSQLFilter(region, prod, contractName), offset, limit)
				.replace("id ,region ,contract_name as contractOfferingName, product", "count(*)");
		return getfilteredCount(sql);
	}

	private List<Predicate<RegionContract>> getAllRequiredPredicate(String region, String prod, String contractName) {

		List<Predicate<RegionContract>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(region)) {
			Predicate<RegionContract> regionP = (Predicate<RegionContract>) u -> u.getRegion().equalsIgnoreCase(region);
			allPredicates.add(regionP);
		}
		if (StringUtils.hasText(prod)) {
			Predicate<RegionContract> prodP = (Predicate<RegionContract>) u -> u.getProduct().equalsIgnoreCase(prod);
			allPredicates.add(prodP);
		}
		if (StringUtils.hasText(contractName)) {
			Predicate<RegionContract> contractNameP = (Predicate<RegionContract>) u -> u.getContractOfferingName()
					.equalsIgnoreCase(contractName);
			allPredicates.add(contractNameP);
		}

		return allPredicates;
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String region, String prod, String contractName) {
		String sql = "";
		if (StringUtils.hasText(region)) {
			sql = sql.concat(" Upper(region)=Upper('" + region + "')");
		}
		if (StringUtils.hasText(prod)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(product)=Upper('" + prod + "')");
		}
		if (StringUtils.hasText(contractName)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(contract_name)=Upper('" + contractName + "')");
		}
		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public ResponseObj<RegionContract> getRegionContract(Map<String, String> reqParam) {
		return getCriteriaQuery(reqParam);
	}

	@Value("${searchInRegionContractExpr}")
	private String searchInRegionContractExpr;
	
	@Value("${searchByUniqueRCProduct}")
	private String searchByUniqueRCProduct;

	@Override
	public ResponseObj<RegionContract> getRegionContractFS(Map<String, String> reqParam) {
		ResponseObj<RegionContract> response = new ResponseObj<>();
		String searchparam = reqParam.get("searchFilter");
		String uniqueparam = reqParam.get("unique");

		if (searchparam != null) {
			response.setData((List<RegionContract>) getFreeSearch(searchInRegionContractExpr, searchparam));
		} else if (uniqueparam != null) {
			response.setData((List<RegionContract>) getFreeSearch(searchByUniqueRCProduct, uniqueparam));
		} else {
			response.setData((List<RegionContract>) findAll());
		}
		response.setCount(0L);
		return response;
	}

}
