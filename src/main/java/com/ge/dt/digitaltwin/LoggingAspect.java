/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 1)
public class LoggingAspect {
	private long starttime = System.currentTimeMillis();
    private static Logger logger = LogManager.getLogger();

	@Before("com.ge.dt.digitaltwin.SystemArchitecture.methodLoggerAop()")
	public void logBefore(JoinPoint joinPoint) {
		starttime = System.currentTimeMillis();
		logger.info("Methode Start : " + joinPoint.getSignature().getName());
	}

	@After("com.ge.dt.digitaltwin.SystemArchitecture.methodLoggerAop()")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("Methode End : " + joinPoint.getSignature().getName() + " took "
				+ (System.currentTimeMillis() - starttime) + "ms execution.");
	}
}