package com.sist1.hr14;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	Logger log = Logger.getLogger(this.getClass());
	
	
	public Object performLog(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		String method = pjp.getSignature().getName();
		//---------------------------------------------
		//				시간 측정 Start
		//---------------------------------------------
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//---------------------------------------------
		//				매소드 실행
		//---------------------------------------------
		try {
			pjp.proceed();
		} catch (Throwable e) {
			log.debug("==============================");
			log.debug(e.getMessage());
			log.debug("==============================");
			throw e;
		}
		
		//---------------------------------------------
		//				시간 측정 Stop
		//---------------------------------------------
		stopWatch.stop();
		log.debug("==============================");
		log.debug(method+"() 메소드 수행에 걸린 시간: "+stopWatch.getTotalTimeMillis()+"(ms) 초");
		log.debug("==============================");
		return obj;
	}
}
