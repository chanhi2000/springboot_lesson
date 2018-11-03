package com.sist1.hr7;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Application Context 또는 BeanFactory 
 */
@Configuration
public class DaoFactory {
	/**
	 * DaoFactory  
	 * @return UserDao
	 */
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connection());
        return userDao;
	}
	
	private ConnectionMaker connection() {
		return new NConnectionMaker();
	}
}
