package com.ge.dt.digitaltwin.service;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, D, PK extends Serializable> {

	public D findById(PK id);
	public List<D> findAll();
	public T create(D createDto);
	public void delete(PK id);
	public D update(D updateDto, PK id);
		
}