package com.sist1.hr3;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sist1.hr3.Main;
import com.sist1.hr3.User;
import com.sist1.hr3.UserDao;

/**
 * Hello world!
 *
 */
public class Main {
	// 115 110
	static final Logger log = Logger.getLogger(Main.class);
	
	public static void main( String[] args ) {
		User user = new User("mmmark_110", "이찬희", "1224");
        UserDao dao = new NUserDao();
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
        } catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        }
        
    }
}
