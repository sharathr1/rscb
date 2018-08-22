/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ge.dt.digitaltwin.dao.QuestionDao;
import com.ge.dt.digitaltwin.dao.UserProfileDao;
import com.ge.dt.digitaltwin.domain.Questions;
import com.ge.dt.digitaltwin.domain.ResponseObj;
import com.ge.dt.digitaltwin.domain.UserProfile;

@Service
public class UserProfileImpl implements IUserProfile {

	@Autowired
	UserProfileDao userDao;

	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	JdbcTemplate jdbcTemp;

	@SuppressWarnings("unchecked")
	@Override
	public ResponseObj<UserProfile> getAllQuestions(Long ssoId) {
		ResponseObj<UserProfile> res = new ResponseObj<>();
		res.setData(userDao.findAll());
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseObj<Questions> getRecentQuestion(Long ssoId) {
		List<Questions> question = new ArrayList<>();
		ResponseObj<Questions> res = new ResponseObj<>();
		if(questionDao.getRecentQuestionForUser(ssoId).size()>0)
		question.add(questionDao.getRecentQuestionForUser(ssoId).get(0));
		res.setData(question);
		return res;
	}

	@Override
	public ResponseObj<Questions> addQuestion(Questions question) {
		System.out.println(question.getId()+"  "+question.getUserId());
		ResponseObj<Questions> res = new ResponseObj<>();
		questionDao.save(question);
		res.setData(null);
		return res;
	}

}
