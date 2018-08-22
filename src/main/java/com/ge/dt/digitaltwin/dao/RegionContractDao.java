/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.dt.digitaltwin.domain.RegionContract;
@Repository
public interface RegionContractDao extends JpaRepository<RegionContract, Long>  {

}