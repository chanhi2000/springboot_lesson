package com.sist.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sist.hr.user.dao.UserDao;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.User;

//@ImportResource({"/applicationContext.xml"})
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BootEx0406ApplicationTests {
    private final Logger LOG = LoggerFactory.getLogger(BootEx0406ApplicationTests.class);
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc mockMvc;
    
	@Autowired
	UserDao dao;
	
	private User user01;
	private User user02;
	private User user03;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+webApplicationContext="+webApplicationContext);
		LOG.info("++++++++++++++++++++++++");
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+mockMvc="+mockMvc);
		LOG.info("++++++++++++++++++++++++");		
		
		user01 = new User("j01_115","Spring Boot","spring1234",Level.BASIC,1,0,"jamesol01@paran.com","2018-11-17");
		user02 = new User("j02_115","Spring Boot","spring1234",Level.SILVER,51,10,"jamesol02@paran.com","2018-11-17");
		user03 = new User("j03_115","Spring Boot","spring1234",Level.GOLD,51,31,"jamesol03@paran.com","2018-11-17");
		
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+userDao="+dao);
		LOG.info("++++++++++++++++++++++++");
	}
	
	@Test
	public void get() throws Exception {

		MockHttpServletRequestBuilder createMessage =
				MockMvcRequestBuilders.get("/user/do_get.do")
				.param("u_id", "j01_112")
				.param("name", "이상무")
				;
		
		mockMvc.perform(createMessage)
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	
	
	
	@Test
	@Ignore
	public void count()throws SQLException, ClassNotFoundException{

		 
		 dao.delAll();
		 
		 dao.add(user01);
		 assertThat(dao.getCount("115"), is(1));
		 
		 dao.add(user02);
		 assertThat(dao.getCount("115"), is(2));
		 
		 dao.add(user03);
		 assertThat(dao.getCount("115"), is(3));		 
		
	}
	
	@Test
	@Ignore
	public void addAndGet()throws SQLException, ClassNotFoundException{
	//Spring은 SingleTon으로 객체를 생성한다.
	//1.객체 2개 생성확인
	LOG.debug("=============================");
	LOG.debug("=dao="+dao);
	LOG.debug("=============================");
	
	
	//--------------------------------
	//단건 삭제
	//--------------------------------	
	int result = dao.del(user01.getU_id());
	if(result>0) {
		LOG.debug("=============================");
		LOG.debug("=del result="+result);
		LOG.debug("=============================");
	}
	
	//--------------------------------
	//단건 등록
	//--------------------------------			
	dao.add(user01);
	
	//--------------------------------
	//단건 조회
	//--------------------------------				
	User inUser = dao.get(user01.getU_id());
	
	//--------------------------------
	//비교
	//--------------------------------				

	checkSampleUser(user01,inUser);
	
	}
	
	private void checkSampleUser(User user1,User user2) {
		assertThat(user1.getU_id(),is(user2.getU_id()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
		
		assertThat(user1.gethLevel(),is(user2.gethLevel()));
		assertThat(user1.getLogin(),is(user2.getLogin()));
		assertThat(user1.getRecommend(),is(user2.getRecommend()));
		assertThat(user1.getEmail(),is(user2.getEmail()));
		
	}

}
