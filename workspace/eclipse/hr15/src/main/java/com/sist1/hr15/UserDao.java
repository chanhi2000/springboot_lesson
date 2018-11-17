package com.sist1.hr15;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

public interface UserDao {

	
	/**
	 * 수정 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int update(User user) throws SQLException;
	
	/**
	 * id조회: OK
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	int getCount(String user_id) throws SQLException;

	/**
	 * 단건 등록
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void add(User user) throws ClassNotFoundException, SQLException;

	/**
	 * 전체 조회
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<User> getAll() throws ClassNotFoundException, SQLException;

	/**
	 * 단건 조회
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;

	/**
	 * 해당 u_id 건 삭제 
	 * @param u_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	int del(String u_id) throws ClassNotFoundException, SQLException;

	/**
	 * 전체 삭제
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void delAll() throws SQLException, ClassNotFoundException;

}