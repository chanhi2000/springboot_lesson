package com.sist1.hr11;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserTest03 {
	Logger log = Logger.getLogger(this.getClass());
	private UserDao dao;
	
	@Autowired
	private ApplicationContext context;
	
	private User user01;
	private User user02;
	private User user03;
	
	@Before
	public void setUp() {
		user01 = new User("j01_115", "Spring Boot", "spring1234");
		user02 = new User("j02_115", "Spring Boot", "spring1234");
		user03 = new User("j03_115", "Spring Boot", "spring1234");
		
		dao = context.getBean("userDao", UserDao.class);
		log.debug("=============================");
		log.debug("setup");
		log.debug("**context**: "+context);
		log.debug("this: "+this);
		log.debug("userDao: "+dao);
		log.debug("=============================");
	}
	
	@Test
	@Ignore
	public void isDaoSingleton() {
		
	}
	
	@Test
	public void count01() {
		log.debug("count01");
		assertThat("1", is("1"));
	}
	
	@Test(expected=NullPointerException.class)
	@Ignore
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		dao.del(user01.get_u_id());
		dao.del(user02.get_u_id());
		dao.del(user03.get_u_id());
		
		assertThat(dao.getCount("115"), is(0));
		
		dao.get("unknown_id");
	}
	
	@Test
	@Ignore
	public void count() throws SQLException, ClassNotFoundException {
		dao.del(user01.get_u_id());
		dao.del(user02.get_u_id());
		dao.del(user03.get_u_id());
		
		dao.add(user01);
		assertThat(dao.getCount("115"), is(1));
		dao.add(user02);
		assertThat(dao.getCount("115"), is(2));
		dao.add(user03);
		assertThat(dao.getCount("115"), is(3));
	}
	
	@Test 
	public void addAndGet() throws SQLException, ClassNotFoundException {
	    User user = new User("mmmark_110", "이찬희", "1224");
        
		// Spring은 Singleton으로 객채를 생성한다.
		log.debug("=============================");
		log.debug("=dao="+dao);
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
	
	@Test
	public void deleteAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		log.debug("count: "+ dao.getCount(""));
		assertThat(dao.getCount(""), is(0));
	}
}
