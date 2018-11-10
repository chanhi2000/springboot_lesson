package com.sist1.hr10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {
	//
	//	IP: 211.238.142.102
	//	 * post: 1521
	//	 * SID: orcl
	//	 * sist/1224
	private static Logger log = Logger.getLogger(UserDao.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Create User into Database
	 * 
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserDao() {
	}
	
	public int getCount(String user_id) throws ClassNotFoundException, SQLException  {
		int count = 0;
		//---------------------------------------------
		// DB 연결
		//--------------------------------------------- 
		Connection c = dataSource.getConnection();
				
		//---------------------------------------------
		// query
		//---------------------------------------------
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) cnt FROM users WHERE u_id LIKE ? \n");
		log.debug("sql:\n" + sb.toString());
		
		//---------------------------------------------
		// 실행
		//---------------------------------------------
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, "%" + user_id + "%");

		//---------------------------------------------
		// 조회
		//---------------------------------------------
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			count = rs.getInt("cnt");
		}
		log.debug("cnt:\n"+count );

		//---------------------------------------------
		// 후 실행
		//---------------------------------------------
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		//---------------------------------------------
		// DB 연결
		//--------------------------------------------- 
		Connection c = dataSource.getConnection();
		
		//---------------------------------------------
		// query
		//---------------------------------------------
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users (u_id, name, password) VALUES (?,?,?) \n");
		log.debug("sql:\n" + sb.toString());
		//---------------------------------------------
		// 실행
		//---------------------------------------------
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user.get_u_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		int flag = ps.executeUpdate();
		log.debug("flag:\n"+flag);
		
		//---------------------------------------------
		// 후 실행
		//---------------------------------------------
		ps.close();
		c.close();
	}
	
	public User get(String user_id) throws ClassNotFoundException, SQLException {
		//---------------------------------------------
		// DB 연결
		//--------------------------------------------- 
		Connection c = dataSource.getConnection();
			
		//---------------------------------------------
		// query
		//---------------------------------------------
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE u_id = ? \n");
		log.debug("sql:\n" + sb.toString());
		
		//---------------------------------------------
		// 실행
		//---------------------------------------------
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user_id);
		User user = null;
		
		//---------------------------------------------
		// 조회
		//---------------------------------------------
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.set_u_id(  rs.getString("u_id")  );
			user.setPassword(  rs.getString("password")  );
			user.setName(  rs.getString("name")  );
		}
		log.debug("user:\n"+user.toString());
		
		//---------------------------------------------
		// 후 실행
		//---------------------------------------------
		rs.close();
		ps.close();
		c.close();
		
		// 예외처리
		if (null == user) {
			throw new EmptyResultDataAccessException(1);
		}
		return user;
	}
	
	public int del(String u_id) throws ClassNotFoundException, SQLException {
		//---------------------------------------------
		// DB 연결
		//--------------------------------------------- 
		Connection c = dataSource.getConnection();
		
		//---------------------------------------------
		// query
		//---------------------------------------------
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM users WHERE u_id = ? \n");
		log.debug("sql:\n" + sb.toString());
		//---------------------------------------------
		// 실행
		//---------------------------------------------
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, u_id);
		
		int flag = ps.executeUpdate();
		log.debug("flag:\n"+flag);
		
		//---------------------------------------------
		// 후 실행
		//---------------------------------------------
		ps.close();
		c.close();
		return flag;
	}
}
