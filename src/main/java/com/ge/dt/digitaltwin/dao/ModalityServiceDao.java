package com.ge.dt.digitaltwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.dt.digitaltwin.domain.ModalityCust;
import com.ge.dt.digitaltwin.domain.ModalityService;
@Repository
public interface ModalityServiceDao extends JpaRepository<ModalityService, Long>  {

}