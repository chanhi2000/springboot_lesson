package com.sist.hr.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.sist.hr.user.domain.User;

public interface UserDao {

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#getCount(java.lang.String)
	 */
	int getCount(String user_id) throws ClassNotFoundException, SQLException;

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#delAll()
	 */
	void delAll() throws SQLException, ClassNotFoundException;

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#del(java.lang.String)
	 */
	int del(String user_id) throws ClassNotFoundException, SQLException;

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#getAll()
	 */
	List<User> getAll() throws ClassNotFoundException, SQLException;

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#get(java.lang.String)
	 */
	User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#add(com.sist.hr.User)
	 */
	int add(User user) throws ClassNotFoundException, SQLException;

	int update(User user) throws SQLException;

}