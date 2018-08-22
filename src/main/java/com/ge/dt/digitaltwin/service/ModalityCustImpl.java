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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.dao.CustomTemplate;
import com.ge.dt.digitaltwin.domain.ModalityCust;
import com.ge.dt.digitaltwin.domain.ResponseObj;

@Service
public class ModalityCustImpl extends GenericDaoImpl<ModalityCust, ModalityCust, Long> implements IModalityCust {
	
	@Autowired
	private CustomTemplate custTemplate;

	@Override
	public List<ModalityCust> getModality(String modality, String customerName, Integer offset, Integer limit,
			boolean fetchAll) {
		if (fetchAll == false) {
			List<Predicate<ModalityCust>> allpredicates = getAllRequiredPredicate(modality, customerName);
			if (allpredicates.size() > 0) {
				Predicate<ModalityCust> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.modality_cust",
						getAllSQLFilter(modality, customerName), offset, limit)).stream().filter(requiredFilter)
								.collect(Collectors.toList());

			} else {
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.modality_cust",
						getAllSQLFilter(modality, customerName), offset, limit));
			}
		} else {
			return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.modality_cust",
					getAllSQLFilter(modality, customerName), offset, limit));
		}
	}

	@Override
	public Long getFilteredCount(String modality, String customerName, Integer offset, Integer limit,
			boolean fetchAll) {
		String sql = custTemplate
				.sqlBuilder("datafabric_dea.modality_cust", getAllSQLFilter(modality, customerName), offset, limit)
				.replace("*", "count(*)");
		return getfilteredCount(sql);
	}

	private List<Predicate<ModalityCust>> getAllRequiredPredicate(String modality, String customerName) {

		List<Predicate<ModalityCust>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(customerName)) {
			Predicate<ModalityCust> customerNameP = (Predicate<ModalityCust>) u -> u.getCustomerName()
					.equalsIgnoreCase(customerName);
			allPredicates.add(customerNameP);
		}
		if (StringUtils.hasText(modality)) {
			Predicate<ModalityCust> modalityP = (Predicate<ModalityCust>) u -> u.getModality()
					.equalsIgnoreCase(modality);
			allPredicates.add(modalityP);
		}
		return allPredicates;
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String modality, String customerName) {
		String sql = "";
		if (StringUtils.hasText(modality)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql=sql.concat(" Upper(modality)=Upper('" + modality + "')");
		}

		if (StringUtils.hasText(customerName)) {
			sql = (StringUtils.hasText(sql)) ? sql.concat(" AND ") : sql;
			sql=sql.concat(" Upper(customer_name)=Upper('" + customerName + "')");
		}

		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public ResponseObj<ModalityCust> getModality(Map<String, String> reqParam) {
		return getCriteriaQuery(reqParam);
	}

}
