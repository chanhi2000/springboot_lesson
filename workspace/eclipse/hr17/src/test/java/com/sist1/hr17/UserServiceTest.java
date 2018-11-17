package com.sist1.hr17;

import static com.sist1.hr17.UserService.MIN_GOLD_RECOMMEND_COUNT;
import static com.sist1.hr17.UserService.MIN_SILVER_LOGIN_COUNT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	
	private List<User> list;
	
	@Test
	public void bean() {
		log.debug("this.userService: "+this.userService);
		assertThat(this.userService, is(notNullValue()));
	}
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				new User("j01_115","Spring Boot0a","spring1234",Level.BASIC,MIN_SILVER_LOGIN_COUNT-1,0,"jamesol01@paran.com","2018-11-17"),
				new User("j02_115","Spring Boot0b","spring1234",Level.BASIC,MIN_SILVER_LOGIN_COUNT,4,"jamesol02@paran.com","2018-11-17"),
				new User("j03_115","Spring Boot0c","spring1234",Level.SILVER,MIN_SILVER_LOGIN_COUNT,MIN_GOLD_RECOMMEND_COUNT-1,"jamesol03@paran.com","2018-11-17"),
				new User("j04_115","Spring Boot0d","spring1234",Level.SILVER,MIN_SILVER_LOGIN_COUNT,MIN_GOLD_RECOMMEND_COUNT,"jamesol03@paran.com","2018-11-17"),
				new User("j05_115","Spring Boot0e","spring1234",Level.GOLD,100,100,"jamesol03@paran.com","2018-11-17")	
		);
	}
	
	
	@Test
	public void add() throws ClassNotFoundException, SQLException {
		// data level 이 있는 경우 (정상)
		// level이 있는 경우 (정상
		// level 이 null 인 경우 처리
		// 조회후 비교
		
		//------------------------------
		//     전체 data 삭제
		//------------------------------
		this.userDao.delAll();
		
		//------------------------------
		//     level 이 있는 경우 (정상) , Level이  null인 경우 처리
		//------------------------------
		User userWithLevel = this.list.get(4);
		User userWithoutLevel = this.list.get(0);
		userWithoutLevel.sethLevel(null);
		
		//------------------------------
		//     service add() 메소드 call
		//------------------------------
		this.userService.add(userWithLevel);
		this.userService.add(userWithoutLevel);
		
		//------------------------------
		//     조회 후 비교
		//------------------------------
		User userWithLevelRead = this.userDao.get(userWithLevel.getU_id());
		User userWithoutLevelRead = this.userDao.get(userWithoutLevel.getU_id());
		
		assertThat(userWithLevelRead.gethLevel(), is(userWithLevel.gethLevel()));
		assertThat(userWithoutLevelRead.gethLevel(), is(Level.BASIC));
		
	}
	
	@Test(expected=RuntimeException.class)
	public void upgradeLevels() 
			throws ClassNotFoundException, SQLException, IllegalAccessException {
		// 전체 삭제
		// list data 추가
		// 등업 수행
		// 비교
		
		//------------------------------
		//     전체삭제
		//------------------------------
		userDao.delAll();
		
		//------------------------------
		//     list data 추가
		//------------------------------
		for (User user: list) {
			userDao.add(user);
		}
		
		//------------------------------
		//     등업 수행
		//------------------------------
		this.userService.upgradeLevelsTx();
		
		//------------------------------
		//     비교
		//------------------------------
		checkLevelIfUpgraded(list.get(0), false);
		checkLevelIfUpgraded(list.get(1), true);
		checkLevelIfUpgraded(list.get(2), false);
		checkLevelIfUpgraded(list.get(3), true);
		checkLevelIfUpgraded(list.get(4), false);
	}
	
	private void checkLevelIfUpgraded(User user, boolean upgraded) throws ClassNotFoundException, SQLException {
		User userUpdated = userDao.get(user.getU_id());
		if (upgraded) 			assertThat(userUpdated.gethLevel(), is(user.gethLevel().nextLevelValue()));
		else 					assertThat(userUpdated.gethLevel(), is(user.gethLevel()));
	}
}
