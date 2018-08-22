/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ge.dt.digitaltwin.domain.RegionContract;

@Component
public class CustomTemplate {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getSelectSQL(String table) {
		if (table.equalsIgnoreCase("datafabric_dea.region_contract")) {
			return "SELECT id ,region ,contract_name as contractOfferingName, product FROM " + table;
		}
		return "SELECT * FROM " + table;
	}

	public String concatWhereClass(String sql, String filter) {
		return sql + filter;
	}

	public String getOffsetLimt(Integer offset, Integer limit) {
		
		
		return " offset " + offset + " limit " + limit;
	}

	public String getSQL(String table, Integer offset, Integer limit) {
		return getSelectSQL(table).concat(getOffsetLimt(offset, limit));
	}

	public String sqlBuilder(String table, String filter, Integer offset, Integer limit) {
		System.out.println(concatWhereClass(getSelectSQL(table), filter).concat(getOffsetLimt(offset, limit)));
		return concatWhereClass(getSelectSQL(table), filter).concat(getOffsetLimt(offset, limit));
	}
}
