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
import com.ge.dt.digitaltwin.domain.CustomerIB;
import com.ge.dt.digitaltwin.domain.ResponseObj;

@Service
public class CustomerIBImpl extends GenericDaoImpl<CustomerIB, CustomerIB, Long> implements ICustomerIB {

	@Value("${searchByCustName}")
	private String searchByCustName;
	
	@Autowired
	private CustomTemplate custTemplate;

	@Override
	public List<CustomerIB> getCustomerIB(String customerName, int ib, Integer offset, Integer limit,
			boolean fetchAll) {
		if (fetchAll == false) {
			List<Predicate<CustomerIB>> allpredicates = getAllRequiredPredicate(customerName);
			if (allpredicates.size() > 0) {
				Predicate<CustomerIB> requiredFilter = allpredicates.get(0);
				for (int i = 1; i < allpredicates.size(); i++) {
					requiredFilter = requiredFilter.and(allpredicates.get(i));
				}
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.customer_ib", getAllSQLFilter(customerName),
						offset, limit)).stream().filter(requiredFilter).collect(Collectors.toList());
			} else {
				return getfilteredData(custTemplate.sqlBuilder("datafabric_dea.customer_ib", getAllSQLFilter(customerName),
						offset, limit));
			}
		} else {
			return getfilteredData(
					custTemplate.sqlBuilder("datafabric_dea.customer_ib", getAllSQLFilter(customerName), offset, limit));
		}
	}

	public List<Predicate<CustomerIB>> getAllRequiredPredicate(String customerName) {
		List<Predicate<CustomerIB>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(customerName)) {
			Predicate<CustomerIB> customerNameP = (Predicate<CustomerIB>) u -> u.getCustomerName()
					.equalsIgnoreCase(customerName);
			allPredicates.add(customerNameP);
		}
		return allPredicates;
	}

	@SuppressWarnings("unused")
	private String getAllSQLFilter(String customerName) {
		String sql = "";
		if (StringUtils.hasText(customerName)) {
			sql=sql.concat(" Upper(customer_name)=Upper('" + customerName + "')");
		}
		return (StringUtils.hasText(sql)) ? " where " + sql + " " : sql;
	}

	@Override
	public Long getFilteredCount(String customerName, int ib, Integer offset, Integer limit, boolean fetchAll) {
		String sql = custTemplate.sqlBuilder("datafabric_dea.customer_ib", getAllSQLFilter(customerName), offset, limit)
				.replace("*", "count(*)");
		return getfilteredCount(sql);
	}

	@Override
	public ResponseObj<CustomerIB> getCustomerIB(Map<String, String> reqParam) {
		ResponseObj<CustomerIB> response = new ResponseObj<>();
		String searchparam = reqParam.get("searchFilter");
		String uniqueparam = reqParam.get("unique");
		if (searchparam != null) {
			response.setData((List<CustomerIB>) getFreeSearch(searchByCustName, searchparam));
		} else if (uniqueparam != null) {
			response.setData((List<CustomerIB>) getFreeSearch(searchByCustName, uniqueparam));
		} else {
			response.setData((List<CustomerIB>) findAll());
		}
		response.setCount(0L);
		return response;
	}
}
