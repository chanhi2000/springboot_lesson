package com.sist1.hr10;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest01 {
	Logger log = Logger.getLogger(this.getClass());
	
	private UserDao dao;
	
	// 선행 메소드 : @Test가 수행 되기 전 1회 수행.
	
	@Before
	public void setUp() {
		ApplicationContext context 
    		= new GenericXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		log.debug("===================================");
		log.debug("1. setUp");
		log.debug("===================================");
	}
	
	@Test
	public void addAndGet() {
		log.debug("addAndGet");
	}
	
	@Test 
	public void count() {
		log.debug("count");
	}
	
	@After
	public void tearDown() {
		log.debug("===================================");
		log.debug("1. setUp");
		log.debug("===================================");
	}

}

/*
 2018-11-10 10:50:28,968 DEBUG [main] hr10.UserTest01   (UserTest01.java:22)     - ===================================
2018-11-10 10:50:28,968 DEBUG [main] hr10.UserTest01   (UserTest01.java:23)     - 1. setUp
2018-11-10 10:50:28,968 DEBUG [main] hr10.UserTest01   (UserTest01.java:24)     - ===================================
2018-11-10 10:50:28,968 DEBUG [main] hr10.UserTest01   (UserTest01.java:29)     - addAndGet
2018-11-10 10:50:28,969 DEBUG [main] hr10.UserTest01   (UserTest01.java:39)     - ===================================
2018-11-10 10:50:28,969 DEBUG [main] hr10.UserTest01   (UserTest01.java:40)     - 1. setUp
2018-11-10 10:50:28,969 DEBUG [main] hr10.UserTest01   (UserTest01.java:41)     - ===================================

2018-11-10 10:50:29,016 DEBUG [main] hr10.UserTest01   (UserTest01.java:23)     - 1. setUp
2018-11-10 10:50:29,016 DEBUG [main] hr10.UserTest01   (UserTest01.java:24)     - ===================================
2018-11-10 10:50:29,016 DEBUG [main] hr10.UserTest01   (UserTest01.java:34)     - count
2018-11-10 10:50:29,016 DEBUG [main] hr10.UserTest01   (UserTest01.java:39)     - ===================================
2018-11-10 10:50:29,017 DEBUG [main] hr10.UserTest01   (UserTest01.java:40)     - 1. setUp
2018-11-10 10:50:29,017 DEBUG [main] hr10.UserTest01   (UserTest01.java:41)     - ===================================
 */ 
