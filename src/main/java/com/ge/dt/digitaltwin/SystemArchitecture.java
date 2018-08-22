/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {
	// Point cut for all productionscheduling api
	@Pointcut("execution(* com.ge.dt.digitaltwin..*.*(..))")
	public void methodLoggerAop() {
	}

	// Point cut for all api
	@Pointcut("execution(* com.ge.dt.digitaltwin.api..*.*(..))")
	public void isControllerLayer() {
	}

//	// Point cut for service layer api
//	@Pointcut("execution(* com.ge.dt.digitaltwin.service..*.*(..))")
//	public void inServiceLayer() {
//	}
}