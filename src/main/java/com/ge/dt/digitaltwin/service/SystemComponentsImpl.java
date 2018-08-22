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
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.dao.CustomTemplate;
import com.ge.dt.digitaltwin.domain.ResponseObj;
import com.ge.dt.digitaltwin.domain.SystemComponents;

@Service
// @Configuration
// @PropertySource("classpath:sql.properties")
public class SystemComponentsImpl extends GenericDaoImpl<SystemComponents, SystemComponents, Long>
		implements ISystemComponents {

	@Autowired
	private CustomTemplate custTemplate;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${searchByComponentsdesc}")
	private String searchByComponentsdesc;
	
	@Value("${searchByUniqueCompDesc}")
	private String searchByUniqueCompDesc;

	@Override
	public List<SystemComponents> getSystemComponents(String systemId, String compDesc, Integer offset, Integer limit,
			boolean fetchAll) {
		if (fetchAll == false) {
			List<Predicate<SystemComponents>> allpredicates = getAllRequiredPredicate(systemId, compDesc);
			if (allpredicates.size() > 0) {
				Predicate<SystemComponents> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.system_component",
						getAllSQLFilter(systemId, compDesc), offset, limit)).stream().filter(requiredFilter)
								.collect(Collectors.toList());
			} else {
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.system_component",
						getAllSQLFilter(systemId, compDesc), offset, limit));
			}
		} else {
			return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.system_component",
					getAllSQLFilter(systemId, compDesc), offset, limit));
		}
	}

	private List<Predicate<SystemComponents>> getAllRequiredPredicate(String systemId, String compDesc) {

		List<Predicate<SystemComponents>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(systemId)) {
			Predicate<SystemComponents> systemIdP = (Predicate<SystemComponents>) u -> u.getSystemId()
					.equalsIgnoreCase(systemId);
			allPredicates.add(systemIdP);
		}
		if (StringUtils.hasText(compDesc)) {
			Predicate<SystemComponents> prodP = (Predicate<SystemComponents>) u -> u.getComponentsDesc()
					.equalsIgnoreCase(compDesc);
			allPredicates.add(prodP);
		}
		return allPredicates;
	}

	@Override
	public Long getFilteredCount(String systemId, String compDesc, Integer offset, Integer limit, boolean fetchAll) {
		String sql = custTemplate
				.sqlBuilder("datafabric_dea.system_component", getAllSQLFilter(systemId, compDesc), offset, limit)
				.replace("*", "count(*)");
		return getfilteredCount(sql);
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String systemId, String compDesc) {
		String sql = "";
		if (StringUtils.hasText(systemId)) {
			sql = sql.concat(" Upper(systemid)=Upper('" + systemId + "')");
		}
		if (StringUtils.hasText(compDesc)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(componentsdesc)=Upper('" + compDesc + "')");
		}
		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public ResponseObj<SystemComponents> getSystemComponents(Map<String, String> reqParam) {
		ResponseObj<SystemComponents> response = new ResponseObj<>();
		String searchparam = reqParam.get("searchFilter");
		String uniqueparam = reqParam.get("unique");
		if (searchparam != null) {
			response.setData((List<SystemComponents>) getFreeSearch(searchByComponentsdesc, searchparam));
		} else if (uniqueparam != null) {
			response.setData((List<SystemComponents>) getFreeSearch(searchByUniqueCompDesc, uniqueparam));
		} else {
			response.setData((List<SystemComponents>) findAll());

		}
		response.setCount(0L);
		return response;
	}

}
