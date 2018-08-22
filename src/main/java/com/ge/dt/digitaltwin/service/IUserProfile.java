/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.service;

import com.ge.dt.digitaltwin.domain.Questions;
import com.ge.dt.digitaltwin.domain.ResponseObj;
import com.ge.dt.digitaltwin.domain.UserProfile;

public interface IUserProfile {
	
	ResponseObj<UserProfile> getAllQuestions(Long ssoId);

	ResponseObj<Questions> getRecentQuestion(Long ssoId);

	ResponseObj<Questions> addQuestion(Questions question);

}
