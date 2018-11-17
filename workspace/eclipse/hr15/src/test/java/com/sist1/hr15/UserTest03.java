package com.sist1.hr15;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
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
		user01 = new User("j01_115", "Spring Boot", "spring1234", Level.BASIC, 1, 0, "j01_115@gmail.com", "2018-11-17");
		user02 = new User("j02_115", "Spring Boot", "spring1234", Level.SILVER, 51, 11, "j02_115@gmail.com", "2018-11-17");
		user03 = new User("j03_115", "Spring Boot", "spring1234", Level.BASIC, 51, 32, "j03_115@gmail.com", "2018-11-17");
		
		dao =  (UserDao) context.getBean("userDao");
		log.debug("=============================");
		log.debug("setup");
		log.debug("**context**: "+context);
		log.debug("this: "+this);
		log.debug("userDao: "+dao);
		log.debug("=============================");
	}
	
	
	
	@Test
	@Ignore
	public void update() throws ClassNotFoundException, SQLException {
		//전체 삭제
		// 단건 (1) 입력
		// 수정 Data 생성 / 수정
		// 수정 Data 조회
		// 비교 검사
		
		//-----------------------------------
		//     전체 삭제
		//-----------------------------------
		dao.delAll();
		
		//-----------------------------------
		//     단건 1 입력
		//-----------------------------------
		user02 = new User("j02_115", "Spring Boot_9", "spring1234", Level.BASIC, 49, 2, "j02_141@gmail.com", "2018-11-17");
		
		//-----------------------------------
		//     수정 Data 생성
		//-----------------------------------
		User userVS = new User("j02_115", "Spring Boot_12", "spring1234", Level.BASIC, 50, 5, "j02_141@gmail.com", "2018-11-17");
		int flag = dao.update(userVS);
		log.debug("flag: "+flag);
		
		//-----------------------------------
		//     수정 Data 조회
		//-----------------------------------
		User userM = dao.get(user01.get_u_id());
		
		//-----------------------------------
		//     비교 검사
		//-----------------------------------
		this.checkSampleUser(userVS, userM);
		
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	@Ignore
	public void getUserFailure() throws SQLException, ClassNotFoundException, EmptyResultDataAccessException {
		dao.del(user01.get_u_id());
		dao.del(user02.get_u_id());
		dao.del(user03.get_u_id());
		
		assertThat(dao.getCount(""), is(0));
		
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
	    User user = new User("mmmark_110", "이찬희", "1224", Level.BASIC, 1, 0, "j01_115@gmail.com", "2018-11-17");
        
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
			assertThat(user.get_u_id(), is(inUser.get_u_id()));
			assertThat(user.getName(), is(inUser.getName()));
			assertThat(user.getPassword(), is(inUser.getPassword()));
			
			
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
	@Ignore
	public void deleteAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		log.debug("count: "+ dao.getCount(""));
		assertThat(dao.getCount(""), is(0));
	}
	
	@Test
	@Ignore
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		List<User> list = dao.getAll();
		log.debug("list: "+list);
		assertThat(list.size(), is(0));
	}
	
	
	private void checkSampleUser(User user1, User user2) {
		assertThat(user1.get_u_id(),is(user2.get_u_id()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
		
		assertThat(user1.gethLevel(),is(user2.gethLevel()));
		assertThat(user1.getLogin(),is(user2.getLogin()));
		assertThat(user1.getRecommend(),is(user2.getRecommend()));
		assertThat(user1.getEmail(),is(user2.getEmail()));
	}
}
