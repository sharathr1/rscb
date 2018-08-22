/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ge.dt.digitaltwin.domain.BaseEntity;
import com.ge.dt.digitaltwin.domain.ResponseObj;

@Service
public abstract class GenericDaoImpl<T, D, PK extends Serializable> implements GenericDao<T, D, PK> {
	private static final Logger log = (Logger) LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private JpaRepository<T, PK> dao;
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private EntityManager entityManager;

	protected Class<T> entityClass;
	protected Class<D> dtoClass;

	protected List<String> entitykeys;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		super();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		this.dtoClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
		this.entitykeys = new ArrayList<>();
		this.entitykeys.add(BaseEntity.class.getDeclaredFields()[0].getName());
		Arrays.stream(entityClass.getDeclaredFields()).forEach(i -> {
			this.entitykeys.add(i.getName());
		});
	}

	@Transactional(readOnly = true)
	public D findById(PK id) {
		return mapper.map(dao.findById(id), dtoClass);
	}

	@Transactional(readOnly = true)
	public Long getCount() {
		Long count = dao.count();
		System.out.println("count " + count);
		return count;
	}

	@Transactional(readOnly = true)
	public List<D> findAll() {
		List<D> result = new ArrayList<D>();
		for (T t : dao.findAll()) {
			result.add(mapper.map(t, dtoClass));
		}
		return result;
	}

	@Transactional
	public T create(D createDto) {
		return dao.saveAndFlush(mapper.map(createDto, entityClass));
	}

	@Transactional
	public void delete(PK id) {
		if (dao.existsById(id))
			dao.deleteById(id);
	}

	@Transactional
	public D update(D updateDto, PK id) {
		@SuppressWarnings("unchecked")
		T t = (T) dao.findById(id);
		if (t != null) {
			BeanUtils.copyProperties(updateDto, t);
			dao.saveAndFlush(t);
			return mapper.map(t, dtoClass);
		} else {
			log.info("Cannot update entity  {},indentified by PK {} ", entityClass.getSimpleName(), id.toString());
			return null;
		}

	}

	@Transactional
	public List<T> getfilteredData(String sqlFilter) {
		List<T> regionExpList = new ArrayList<>();
		System.out.println(sqlFilter);
		regionExpList = template.query(sqlFilter, new BeanPropertyRowMapper<T>(entityClass));
		return regionExpList;
	}

	@Transactional
	public Long getfilteredCount(String sqlFilter) {
		Long count = template.queryForObject(sqlFilter, Long.class);
		return count;
	}
	@Transactional
	public List<T> getFreeSearch(String sqlFilter, String searchparam) {
		List<T> regionExpList = new ArrayList<>();
		System.out.println(sqlFilter);
		regionExpList = template.query(sqlFilter,new Object[] { searchparam },new BeanPropertyRowMapper<T>(entityClass));
		return regionExpList;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public ResponseObj<T> getCriteriaQuery(Map<String, String> reqParam) {
		System.out.println("Entity :" + entityClass.getSimpleName() + " tableName "
				+ entityClass.getAnnotation(Table.class).name());
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery cq = qb.createQuery();
		Root<T> rootEntity = cq.from(entityClass);
		int offset = 0;
		int limit = 5;
		if (reqParam.get("offset") != null) {
			offset = Integer.parseInt(reqParam.get("offset"));
		}
		if (reqParam.get("limit") != null) {
			limit = Integer.parseInt(reqParam.get("limit"));
		}

		List<javax.persistence.criteria.Predicate> predicates = new ArrayList<javax.persistence.criteria.Predicate>();

		reqParam.forEach((k, v) -> {
			if (entitykeys.contains(k)) {
				if (v.contains("<")) {
					v = v.replace("<", "");
					predicates.add(qb.lessThan(rootEntity.get(k), v));
				} else if (v.contains(">")) {
					v = v.replace(">", "");
					predicates.add(qb.greaterThan(rootEntity.get(k), v));
				} else if (isNumeric(v)) {
					predicates.add(qb.equal(rootEntity.get(k), v));
				} else {
//					if (v.contains(",")) {
//						List<String> values = Arrays.asList(v.split(","));
//						System.out.println(values.size());
//						predicates.add(qb.in(qb.upper(rootEntity.get(k)), v.toUpperCase()));
//					}
					predicates.add(qb.equal(qb.upper(rootEntity.get(k)), v.toUpperCase()));
				}
			} else {
				if (!(k.equalsIgnoreCase("limit") || k.equalsIgnoreCase("offset")))
					System.out.println("No Such fiels :key " + k);
			}
		});
		System.out.println(predicates.size()+"::"+ offset + " " + limit);
		cq.select(rootEntity).where(predicates.toArray(new javax.persistence.criteria.Predicate[] {}));

		List<D> rs = entityManager.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
		ResponseObj<T> response = new ResponseObj<>();
		response.setData((List<T>) rs);
		response.setCount((long) (entityManager.createQuery(cq).getResultList()).size());
		getcount();
		return response;
	}

	public Long getcount() {
		entityManager.createQuery("SELECT count(*) FROM " + entityClass.getAnnotation(Table.class).name() + " ")
				.getResultList();
		return null;
	}

	public boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
