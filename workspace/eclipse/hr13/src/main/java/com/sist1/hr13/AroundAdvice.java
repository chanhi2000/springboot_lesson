package com.sist1.hr13;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
/**
 * 
 * 메소드 호출 자체를 가로채 비즈니스 메소드 실행 전후에 처리할 로직;
 * @author sist1
 *
 */

public class AroundAdvice {
	
	Logger log = Logger.getLogger(this.getClass());
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("[BEFORE]: 비즈니스 메소드 전에 수행 전에 처리할 내용");
		Object returnObj = pjp.proceed();
		log.debug("[AFTER]: 비즈니스 메소드 후에 수행 전에 처리할 내용");
		return returnObj;
	}
	
}
