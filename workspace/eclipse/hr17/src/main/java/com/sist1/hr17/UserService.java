package com.sist1.hr17;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserService {
	private Logger log = Logger.getLogger(this.getClass());
	
	public static final int MIN_SILVER_LOGIN_COUNT = 50;	// BASIC -> SILVER
	public static final int MIN_GOLD_RECOMMEND_COUNT = 30;	// SILVER -> GOLD
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private PlatformTransactionManager transactionManager;
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/**
	 * 사용자 최초 추가
	 * @param user
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		if (null == user.gethLevel()) user.sethLevel(Level.BASIC);
		userDao.add(user);
		
	}
	
	/**
	 * 등업
	 */
	public void upgradeLevelsTx() throws ClassNotFoundException, SQLException, IllegalAccessException, RuntimeException {
		// 전체조회
		// 등업대상 추출
		// 등업대상에 해당되면 등업
		//-----------------------------------
		//     전체조회
		//-----------------------------------
//		TransactionStatus status = 
//				this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		TransactionSynchronizationManager.initSynchronization();
		Connection c = DataSourceUtils.getConnection(dataSource);
		
		try {
			List<User> list = userDao.getAll();
			
			//-----------------------------------
			//     등업대상 추출
			//-----------------------------------
			int upCnt = 0;
			for (User user : list) {
				// BASIC && loginCnt >= 50
				// SILVER && recommend >= 30
				// GOLD

				// j04_115
				if (user.getU_id().equals("j04_115")) { 
					throw new RuntimeException("사용자 익셉션: "+user.getU_id());
				}	
				
				if (  canUpgradeLevel(user)  ) {
					//-----------------------------------
					//     등업대상에 해당되면 등업
					//-----------------------------------
					upCnt++;
					upgradeLevel(user);
				}
			}
			
			log.debug("==============================");
			log.debug("upCnt="+upCnt);
			log.debug("==============================");
			c.commit();
		} catch (RuntimeException e) {
			log.debug("==============================");
			log.debug("RuntimeException: "+e.getMessage());
			log.debug("==============================");
			c.rollback();
			throw e;
		} finally {
			// 스프링 유틸리티 메소드를 통해 커넥션 닫기
			DataSourceUtils.releaseConnection(c, this.dataSource);
			
			// 돟기화 메소드 종료
			TransactionSynchronizationManager.unbindResource(dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
		
	}
	
	public void upgradeLevel(User user) throws ClassNotFoundException, SQLException {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	public boolean canUpgradeLevel(User user) throws IllegalAccessException {
		Level curLevel = user.gethLevel();
		switch (curLevel) {
		case BASIC:	 return (user.getLogin() >= MIN_SILVER_LOGIN_COUNT);
		case SILVER: return (user.getRecommend() >= MIN_GOLD_RECOMMEND_COUNT);
		case GOLD: 	 return false;
		default: 	throw new IllegalAccessException("Unknown Level:"+curLevel); 
		}
	}
}
