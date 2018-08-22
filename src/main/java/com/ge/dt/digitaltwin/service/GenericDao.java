/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ge.dt.digitaltwin.domain.ResponseObj;

public interface GenericDao <T, D, PK extends Serializable> {

	public D findById(PK id);
	public List<D> findAll();
	public T create(D createDto);
	public void delete(PK id);
	public D update(D updateDto, PK id);
	public Long getCount();
	public Long getfilteredCount(String sqlFilter);
	public ResponseObj<T> getCriteriaQuery(Map<String, String> searchfilter);
}