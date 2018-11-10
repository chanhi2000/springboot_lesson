package com.sist1.hr9;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.BeansException;

import com.sist1.hr9.User;
import com.sist1.hr9.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * 1. method must be public
 * 2. @Test annotation is needed
 * @author sist1
 *
 */

public class UserTest {
	Logger log = Logger.getLogger(this.getClass());
	
	@Test
	public void count01() {
		log.debug("count01");
		assertThat("1", is("1"));
	}
	
	@Test(expected=NullPointerException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		ApplicationContext context 
        	= new GenericXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user01 = new User("j01_115", "Spring Boot", "spring1234");
		User user02 = new User("j02_115", "Spring Boot", "spring1234");
		User user03 = new User("j03_115", "Spring Boot", "spring1234");
		
		userDao.del(user01.get_u_id());
		userDao.del(user02.get_u_id());
		userDao.del(user03.get_u_id());
		
		assertThat(userDao.getCount("115"), is(0));
		
		userDao.get("unknown_id");
	}
	
	@Test
	@Ignore
	public void count() throws SQLException, ClassNotFoundException {
		ApplicationContext context 
	        = new GenericXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user01 = new User("j01_115", "Spring Boot", "spring1234");
		User user02 = new User("j02_115", "Spring Boot", "spring1234");
		User user03 = new User("j03_115", "Spring Boot", "spring1234");
		
		userDao.del(user01.get_u_id());
		userDao.del(user02.get_u_id());
		userDao.del(user03.get_u_id());
		
		userDao.add(user01);
		assertThat(userDao.getCount("115"), is(1));
		userDao.add(user02);
		assertThat(userDao.getCount("115"), is(2));
		userDao.add(user03);
		assertThat(userDao.getCount("115"), is(3));
	}
	
	@Test @Ignore
	public void addAndGet() throws SQLException, ClassNotFoundException {
	    ApplicationContext context 
        = new GenericXmlApplicationContext("/applicationContext.xml");
		User user = new User("mmmark_110", "이찬희", "1224");
        
		// Spring은 Singleton으로 객채를 생성한다.
		// 1. 객체 2개 생성확인
		// 2. 객체 1개 생성확인
		UserDao dao=context.getBean("userDao", UserDao.class);
		UserDao dao01=context.getBean("userDao", UserDao.class);
		
		log.debug("=============================");
		log.debug("=dao="+dao);
		log.debug("=dao01="+dao01);
		log.debug("=============================");
		
		try {
			//---------------------------------------------
			// 단건등록
			//---------------------------------------------
			dao.add(user);
			        	
			//---------------------------------------------
			// 단건확인
			//---------------------------------------------
			User inUser = dao.get(user.get_u_id());
			assertThat(user, is(inUser));
			
			if (user.equals(inUser)) {
				log.debug("===================================");
			    log.debug("등록 성공");
			    log.debug("===================================");
			} else {
				log.debug("===================================");
			    log.debug("등록 실패 !!!");
			    log.debug("===================================");
			}
			        	
			//---------------------------------------------
			// 단건 중복 제거
			//---------------------------------------------
			int isSuccessful = dao.del(user.get_u_id());
			if (isSuccessful>0) {
				log.debug("===================================");
			    log.debug("제거 성공 del result="+isSuccessful);
			    log.debug("===================================");
			} else {
				log.debug("===================================");
			    log.debug("제거 실패 !!!");
			    log.debug("===================================");
			}
		} catch (ClassNotFoundException | SQLException | BeansException e) {
			e.printStackTrace();
		}
	}	
}
