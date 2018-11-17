package com.sist1.hr13;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class LoggingAOP {
	Logger log = Logger.getLogger(this.getClass());
	
	// JoinPoint: 메소드의 param, 메소드이름
	public void logging(JoinPoint joinPoint) {
		Signature method = joinPoint.getSignature();
		String methodName = method.getName();
		log.debug(">>>>>--------------------");
		log.debug(methodName+" is calling");
		log.debug(">>>>>--------------------");
	}
}
