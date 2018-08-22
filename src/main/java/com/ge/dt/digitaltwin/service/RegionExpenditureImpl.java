/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.dao.CustomTemplate;
import com.ge.dt.digitaltwin.domain.RegionExpenditure;
import com.ge.dt.digitaltwin.util.DateUtils;

@Service
public class RegionExpenditureImpl extends GenericDaoImpl<RegionExpenditure, RegionExpenditure, Long>
		implements IRegionExpenditure {

	@Autowired
	private CustomTemplate custTemplate;

	@Autowired
	private DateUtils dateutils;

	@Override
	public List<RegionExpenditure> getRegionExpenditure(String region, String prodDesc, Integer year, Integer mon,
			String periodFrom, String periodTo, Integer offset, Integer limit, boolean fetchAll) {

		if (fetchAll == false) {
			List<Predicate<RegionExpenditure>> allpredicates = getAllRequiredPredicate(region, prodDesc, year, mon);
			if (allpredicates.size() > 0) {
				Predicate<RegionExpenditure> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_expenditure",
						getAllSQLFilter(region, prodDesc, year, mon, periodFrom, periodTo), offset, limit)).stream()
								.filter(requiredFilter).collect(Collectors.toList());
				/**
				 * String sql = "SELECT * FROM digitaltwin.region_expenditure
				 * "+getAllSQLFilter(region,prodDesc, year, mon); return
				 * getfilteredData(sql);
				 */
			} else {
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_expenditure",
						getAllSQLFilter(region, prodDesc, year, mon, periodFrom, periodTo), offset, limit));
			}
		} else {

			return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.region_expenditure",
					getAllSQLFilter(region, prodDesc, year, mon, periodFrom, periodTo), offset, limit));
		}
	}

	private List<Predicate<RegionExpenditure>> getAllRequiredPredicate(String region, String prodDesc, Integer year,
			Integer mon) {

		List<Predicate<RegionExpenditure>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(region)) {
			Predicate<RegionExpenditure> regionP = (Predicate<RegionExpenditure>) u -> u.getRegionDescription()
					.equalsIgnoreCase(region);
			allPredicates.add(regionP);
		}
		if (StringUtils.hasText(prodDesc)) {
			Predicate<RegionExpenditure> prodDescP = (Predicate<RegionExpenditure>) u -> u.getProductDescription()
					.equalsIgnoreCase(prodDesc);
			allPredicates.add(prodDescP);
		}
		if (year > 0) {
			Predicate<RegionExpenditure> yearP = (Predicate<RegionExpenditure>) u -> u.getSrProcessYear()
					.intValue() == year;
			allPredicates.add(yearP);
		}
		if (mon > 0) {
			Predicate<RegionExpenditure> monP = (Predicate<RegionExpenditure>) u -> u.getSrProcessMonth()
					.intValue() == mon;
			allPredicates.add(monP);
		}
		return allPredicates;
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String region, String prodDesc, Integer year, Integer mon, String periodTo,
			String periodFrom) {
		String sql = "";
		if (StringUtils.hasText(region)) {
			sql = sql.concat(" Upper(region_description)=Upper('" + region + "')");
		}
		if (StringUtils.hasText(prodDesc)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" Upper(product_description)=Upper('" + prodDesc + "')");
		}
		if (year > 0) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" sr_process_year=" + year);
		}
		if (mon > 0) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" sr_process_month=" + mon);
		}

		if (StringUtils.hasText(periodFrom) && StringUtils.hasText(periodTo)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql = sql.concat(" ( ").concat(" sr_process_year >=" + dateutils.getYear(periodFrom)).concat(" AND ")
					.concat(" sr_process_year <=" + dateutils.getYear(periodTo));
			sql = sql.concat(" AND ").concat(" sr_process_month >=" + dateutils.getMonth(periodFrom)).concat(" AND ")
					.concat(" sr_process_month <=" + dateutils.getMonth(periodTo)).concat(" ) ");
		}

		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public Long getFilteredCount(String region, String prodDesc, Integer year, Integer mon, String periodTo,
			String periodFrom, Integer offset, Integer limit, boolean fetchAll) {
		String sql = custTemplate
				.sqlBuilder("datafabric_dea.region_expenditure",
						getAllSQLFilter(region, prodDesc, year, mon, periodTo, periodFrom), offset, limit)
				.replace("*", "count(*)");
		return getfilteredCount(sql);
	}

}
