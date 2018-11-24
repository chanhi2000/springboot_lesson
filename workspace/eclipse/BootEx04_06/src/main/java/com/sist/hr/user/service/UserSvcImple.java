package com.sist.hr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.hr.user.dao.UserDao;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.User;

@Service
@Transactional
public class UserSvcImple implements UserSvc {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
	public static final int MIN_SILVER_LOGIN_COUNT   = 50;//BASIC->SILVER
	public static final int MIN_GOLD_RECOMMAND_COUNT = 30;//SILVER->GOLD
	
	
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#del(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#del(java.lang.String)
	 */
	@Override
	public int del(String user_id) throws ClassNotFoundException, SQLException{
		log.info("@Service+++++++++++++++++++++++++");
		log.info("+del++++++++++++++++++++++++");
		log.info("@Service+++++++++++++++++++++++++");
		return userDao.del(user_id);
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#getAll()
	 */
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#getAll()
	 */
	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException{
		log.info("@Service+++++++++++++++++++++++++");
		log.info("+getAll++++++++++++++++++++++++");
		log.info("@Service+++++++++++++++++++++++++");
		return userDao.getAll();		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#get(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#get(java.lang.String)
	 */
	@Override
	public User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException{
		log.info("@Service+++++++++++++++++++++++++");
		log.info("+get++++++++++++++++++++++++");
		log.info("@Service+++++++++++++++++++++++++");
		return userDao.get(user_id);		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#add(com.sist.hr.User)
	 */
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#add(com.sist.hr.user.domain.User)
	 */
	@Override
	public int add(User user) throws ClassNotFoundException, SQLException{
		if(null == user.gethLevel())user.sethLevel(Level.BASIC);
		
		log.info("@Service+++++++++++++++++++++++++");
		log.info("+get++++++++++++++++++++++++");
		log.info("@Service+++++++++++++++++++++++++");
		return userDao.add(user);		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#update(com.sist.hr.user.domain.User)
	 */
	@Override
	public int update(User user) throws SQLException{
		log.info("@Service+++++++++++++++++++++++++");
		log.info("+get++++++++++++++++++++++++");
		log.info("@Service+++++++++++++++++++++++++");
		return userDao.update(user);		
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#upgradeLevelsTx()
	 */
	@Override
	public void upgradeLevelsTx() throws	ClassNotFoundException, SQLException, IllegalAccessException,RuntimeException {
		//전체 조회
		//등업 대상 추출
		//등업 대상에 해당되면 등업
		//---------------------------------------
		//1.전체 조회
		//---------------------------------------	
	
		List<User> list = userDao.getAll();
		
		//---------------------------------------
		//2.등업 대상 추출
		//---------------------------------------
		int upCnt = 0;
		for(User user:list) {
			//Basic  && loginCnt>=50
			//Silver && recommand>=30
			//gold
			
			//임시 Exception발생
			if(user.getU_id().equals("j04_115")) {
				throw new RuntimeException("사용자 익셉션:"+user.getU_id()); 
			}
			
			if(isCanUpgradeLevel(user)) {
				upCnt++;
				upgradeLevel(user);
			}
		}
	}
	
	/**
	 * 
	 * @param user
	 * @throws SQLException 
	 */
	public void upgradeLevel(User user) throws SQLException {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	
	public boolean isCanUpgradeLevel(User user) throws IllegalAccessException {
		Level curLevel = user.gethLevel();
		
		switch(curLevel) {
			case BASIC:  return (user.getLogin()>=MIN_SILVER_LOGIN_COUNT);
			case SILVER: return (user.getRecommend()>=MIN_GOLD_RECOMMAND_COUNT);
			case GOLD:   return false;
			default: throw new IllegalAccessException("Unknown Level:"+curLevel);
		}
	}	
}
