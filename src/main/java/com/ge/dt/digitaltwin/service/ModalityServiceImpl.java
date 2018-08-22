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

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.dao.CustomTemplate;
import com.ge.dt.digitaltwin.domain.ModalityService;
import com.ge.dt.digitaltwin.domain.ResponseObj;

@Service
public class ModalityServiceImpl extends GenericDaoImpl<ModalityService, ModalityService, Long>
		implements IModalityService {

	// private EntityManagerFactory entityManagerFactory =
	// Persistence.createEntityManagerFactory("persistence");
	/*@Autowired
	private EntityManager entityManager;*/
	// = entityManagerFactory.createEntityManager();

	@Autowired
	private CustomTemplate customTemplate;

	@Override
	public List<ModalityService> getModality(String modality, Double age, String region, String sysCovLevWarrantyc,
			Integer offset, Integer limit, boolean fetchAll) {
		if (fetchAll == false) {
			List<Predicate<ModalityService>> allpredicates = getAllRequiredPredicate(modality, age, region,
					sysCovLevWarrantyc);
			if (allpredicates.size() > 0) {
				Predicate<ModalityService> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(customTemplate.sqlBuilder("datafabric_dea.modality_service",
						getAllSQLFilter(modality, age, region, sysCovLevWarrantyc), offset, limit)).stream()
								.filter(requiredFilter).collect(Collectors.toList());
			} else {
				return getfilteredData(customTemplate.sqlBuilder("datafabric_dea.modality_service",
						getAllSQLFilter(modality, age, region, sysCovLevWarrantyc), offset, limit));
			}
		} else {
			return getfilteredData(customTemplate.sqlBuilder("datafabric_dea.modality_service",
					getAllSQLFilter(modality, age, region, sysCovLevWarrantyc), offset, limit));
		}
	}

	@Override
	public Long getFilteredCount(String modality, Double age, String region, String sysCovLevWarrantyc, Integer offset,
			Integer limit, boolean fetchAll) {
		String sql = customTemplate.sqlBuilder("datafabric_dea.modality_service",
				getAllSQLFilter(modality, age, region, sysCovLevWarrantyc), offset, limit).replace("*", "count(*)");
		return getfilteredCount(sql);
	}

	public List<Predicate<ModalityService>> getAllRequiredPredicate(String modality, Double age, String region,
			String sysCovLevWarrantyc) {
		List<Predicate<ModalityService>> allPredicates = new ArrayList<>();
		if (age != null) {
			Predicate<ModalityService> ageP = (Predicate<ModalityService>) u -> u.getAvgAge().doubleValue() == age;
			allPredicates.add(ageP);
		}
		if (StringUtils.hasText(modality)) {
			Predicate<ModalityService> modalityP = (Predicate<ModalityService>) u -> u.getModality()
					.equalsIgnoreCase(modality);
			allPredicates.add(modalityP);
		}
		if (StringUtils.hasText(region)) {
			Predicate<ModalityService> regionP = (Predicate<ModalityService>) u -> u.getRegion()
					.equalsIgnoreCase(region);
			allPredicates.add(regionP);
		}
		if (StringUtils.hasText(sysCovLevWarrantyc)) {
			Predicate<ModalityService> sysCovLevWarrantycP = (Predicate<ModalityService>) u -> u
					.getSystemCoverageLevelWarrantyc().equalsIgnoreCase(sysCovLevWarrantyc);
			allPredicates.add(sysCovLevWarrantycP);
		}
		return allPredicates;
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String modality, Double age, String region, String sysCovLevWarrantyc) {
		String sql = "";
		if (StringUtils.hasText(modality)) {
			sql = sql.concat(" Upper(modality)=Upper('" + modality + "')");
		}
		if (age != null) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" avg_age='" + age + "'");
		}
		if (StringUtils.hasText(region)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(region)=Upper('" + region + "')");
		}
		if (StringUtils.hasText(sysCovLevWarrantyc)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(system_coverage_level_warrantyc)=Upper('" + sysCovLevWarrantyc + "')");
		}
		System.out.println("SQL Filter " + sql);
		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public ResponseObj<ModalityService> getModality(Map<String, String> reqParam) {
		return getCriteriaQuery(reqParam);
	}

}
