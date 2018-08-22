/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.CustomerIB;
import com.ge.dt.digitaltwin.domain.ResponseObj;

public interface ICustomerIB extends GenericDao<CustomerIB, CustomerIB, Long> {

	List<CustomerIB> getCustomerIB(String customerName, int ib,  Integer offset, Integer limit,boolean fetchAll);

	Long getFilteredCount(String customerName, int i, Integer offset, Integer limit, boolean fetchAll);

	ResponseObj<CustomerIB> getCustomerIB(Map<String, String> reqParam);

}
