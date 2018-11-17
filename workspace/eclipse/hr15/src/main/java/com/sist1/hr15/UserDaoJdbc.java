package com.sist1.hr15;

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

public class UserDaoJdbc implements UserDao {
	//
	//	IP: 211.238.142.102
	//	 * post: 1521
	//	 * SID: orcl
	//	 * sist/1224
	private static Logger log = Logger.getLogger(UserDaoJdbc.class);
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
			user.set_u_id(rs.getString("u_id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			
			user.sethLevel(Level.valueOf(rs.getInt("h_level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			user.setEmail(rs.getString("email"));
			user.setRegDt(rs.getString("reg_dt"));
			
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
	public UserDaoJdbc() {
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String user_id) throws SQLException  {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(*) cnt FROM users WHERE u_id LIKE ? \n");
//		return Integer.parseInt(jdbcTemplate.queryForObject(
//				sb.toString(), 
//				new Object[] {"%"+user_id+"%"}, 
//				new RowMapper<String>() {
//					@Override
//					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//						return rs.getString("cnt");
//					}
//				}));
		return this.jdbcTemplate.queryForObject(sb.toString()
				,new Object[] {"%"+user_id+"%"}
		        ,Integer.class);
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#add(com.sist1.hr15.User)
	 */
	@Override
	public void add(final User user) throws ClassNotFoundException, SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users (u_id, name, password, h_level, login, recommend, email, reg_dt) VALUES (?,?,?,?,?,?,?,sysdate) \n");
		log.debug("sql:\n" + sb.toString());
		Object[] args = {user.get_u_id(), 
						 user.getName(), 
						 user.getPassword(), 
						 user.gethLevel().intValue(), 
						 user.getLogin(), 
						 user.getRecommend(), 
						 user.getEmail()};
		jdbcTemplate.update(sb.toString(), args);
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#getAll()
	 */
	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		return jdbcTemplate.query("SELECT * FROM users ORDER BY u_id \n", userMapper);
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#get(java.lang.String)
	 */
	@Override
	public User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u_id, name, password, h_level, login, recommend, email, TO_CHAR(reg_dt, 'YYYY-MM-DD') as reg_dt FROM users WHERE u_id = ? \n");
		log.debug("sql:\n" + sb.toString());
		return jdbcTemplate.queryForObject(
				sb.toString(), 
				new Object[] {user_id}, 
				userMapper);
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#del(java.lang.String)
	 */
	@Override
	public int del(final String u_id) throws ClassNotFoundException, SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM users WHERE u_id = ? \n");
		log.debug("sql:\n"+sb.toString());
		return jdbcTemplate.update(sb.toString(), u_id);
	}
	
	/* (non-Javadoc)
	 * @see com.sist1.hr15.UserDaoz#delAll()
	 */
	@Override
	public void delAll() throws  SQLException, ClassNotFoundException {
		jdbcTemplate.update("DELETE FROM users");
	}

	@Override
	public int update(User user) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append("UDATE users SET name = ?, password = ? h_level = ?, login = ?, recommend = ?, email = ?, reg_dt = sysdate WHERE u_id = ? \n");
		log.debug("sql:\n"+sb.toString());
		Object[] args = {user.getName(), 
						 user.getPassword(), 
						 user.gethLevel().intValue(), 
						 user.getLogin(), 
						 user.getRecommend(), 
						 user.getEmail(),
						 user.get_u_id()};
		return jdbcTemplate.update(sb.toString(), args);
	}
}
