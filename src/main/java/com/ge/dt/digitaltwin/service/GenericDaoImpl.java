package com.ge.dt.digitaltwin.service;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public abstract class GenericDaoImpl<T, D, PK extends Serializable>
		implements GenericDao<T, D, PK> {
	private static final Logger log = (Logger) LoggerFactory.getLogger(GenericDaoImpl.class);
	
	@Autowired
	private  JpaRepository<T, PK> dao;
	@Autowired
    private  DozerBeanMapper mapper;
	
	protected Class<T> entityClass;
    protected Class<D> dtoClass;
	
    public GenericDaoImpl() {
		super();
	    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	    this.dtoClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
	}

    @Transactional(readOnly=true)
	public D findById(PK id) {
		return mapper.map(dao.findById(id), dtoClass);
	}

    @Transactional(readOnly=true)
	public List<D> findAll(){
		List<D> result = new ArrayList<D>();
        for (T t : dao.findAll()) {
            result.add(mapper.map(t, dtoClass));
        }
        return result;
	}

    @Transactional
	public T create(D createDto){
    	 return dao.saveAndFlush(mapper.map(createDto, entityClass));
	}

    @Transactional
	public void delete(PK id){
    	if ( dao.existsById(id)) dao.deleteById(id);
	}

    @Transactional
	public D update(D updateDto, PK  id){ 
    	T t= (T) dao.findById(id); 
    	if (t!=null){
    		BeanUtils.copyProperties(updateDto, t);
    		dao.saveAndFlush(t);
    		return mapper.map(t, dtoClass);
    	} else{
    		log.info("Cannot update entity  {},indentified by PK {} ",entityClass.getSimpleName(), id.toString());
    		return null;
    	}
    	
	}
    
}
