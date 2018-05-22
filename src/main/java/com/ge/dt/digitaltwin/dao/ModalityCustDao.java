package com.ge.dt.digitaltwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.dt.digitaltwin.domain.ModalityCust;
@Repository
public interface ModalityCustDao extends JpaRepository<ModalityCust, Long>  {

}