package com.sist1.hr4;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sist1.hr4.Main;
import com.sist1.hr4.User;
import com.sist1.hr4.UserDao;

/**
 * Hello world!
 *
 */
public class Main 
{
	// 115 110
		static final Logger log = Logger.getLogger(Main.class);
		
		public static void main( String[] args ) {
			User user = new User("mmmark_110", "이찬희", "1224");
	        UserDao dao = new UserDao();
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
	        } catch (ClassNotFoundException | SQLException e) {
	        	e.printStackTrace();
	        }
	        
	    }
}
