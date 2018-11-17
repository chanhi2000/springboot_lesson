package com.sist1.hr14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	//
	//	IP: 211.238.142.102
	//	 * post: 1521
	//	 * SID: orcl
	//	 * sist/1224
	private static Logger log = Logger.getLogger(UserDao.class);
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	private RowMapper<User> userMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.set_u_id(  rs.getString("u_id")  );
			user.setPassword(  rs.getString("password")  );
			user.setName(  rs.getString("name")  );
			user.setName(  rs.getString("name")  );
			return user;
		}
	};
	
	/**
	 * Create User into Database
	 * 
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserDao() {
	}
	
	public int getCount(String user_id) throws SQLException  {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) cnt FROM users WHERE u_id LIKE ? \n");
		return Integer.parseInt(jdbcTemplate.queryForObject(
				sb.toString(), 
				new Object[] {"%"+user_id+"%"}, 
				new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("cnt");
					}
				}));
	}
	
	
	public void add(final User user) throws ClassNotFoundException, SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users (u_id, name, password) VALUES (?,?,?) \n");
		log.debug("sql:\n" + sb.toString());
		jdbcTemplate.update(sb.toString(), user.get_u_id(), user.getName(), user.getPassword());
	}
	
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		return jdbcTemplate.query("SELECT * FROM users", userMapper);
	}
	
	public User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE u_id = ? \n");
		log.debug("sql:\n" + sb.toString());
		return jdbcTemplate.queryForObject(
				sb.toString(), 
				new Object[] {user_id}, 
				userMapper);
	}
	
	public int del(final String u_id) throws ClassNotFoundException, SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM users WHERE u_id=? \n");
		log.debug("sql:\n"+sb.toString());
		return jdbcTemplate.update(sb.toString(), u_id);
	}
	
	public void delAll() throws  SQLException, ClassNotFoundException {
		jdbcTemplate.update("DELETE FROM users");
	}
}
