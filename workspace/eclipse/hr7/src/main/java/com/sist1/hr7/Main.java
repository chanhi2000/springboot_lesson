package com.sist1.hr7;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist1.hr7.DaoFactory;
import com.sist1.hr7.Main;
import com.sist1.hr7.User;
import com.sist1.hr7.UserDao;

/**
 * Hello world!
 *
 */

public class Main {
	static final Logger log = Logger.getLogger(Main.class);
	
	public static void main( String[] args ) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		User user = new User("mmmark_110", "이찬희", "1224");
			        
		// Spring은 Singleton으로 객채를 생성한다.
		// 1. 객체 2개 생성확인
		// 2. 객체 1개 생성확인
		UserDao dao = context.getBean("userDao", UserDao.class);
//		UserDao dao01 = new DaoFactory().userDao();
		log.debug("===================================");
	    log.debug("dao = "+dao);
//	    log.debug("dao01 = "+dao01);
	    log.debug("===================================");
		
		try {
			
		    
			//---------------------------------------------
			// 단건등록
			//---------------------------------------------
			dao.add(user);
			        	
			//---------------------------------------------
			// 단건확인
			//---------------------------------------------
			User inUser = dao.get(user.get_u_id());
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
			if (isSuccessful==1) {
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
