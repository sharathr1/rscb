/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ge.dt.digitaltwin.domain.Questions;
import com.ge.dt.digitaltwin.domain.RegionExpenditure;
import com.ge.dt.digitaltwin.domain.UserProfile;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Long> {

    @Query("SELECT p FROM Questions p WHERE p.userId = :userId order by time desc")
	List<Questions> getRecentQuestionForUser(@Param("userId") Long userId);

}