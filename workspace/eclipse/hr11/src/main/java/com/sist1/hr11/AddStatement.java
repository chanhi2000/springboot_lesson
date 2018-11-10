package com.sist1.hr11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class AddStatement implements StatementStrategy {
	Logger log = Logger.getLogger(this.getClass());
	User user;
	
	public AddStatement() {
	}
	public AddStatement(User user) {
		this.user = user;
	}

	@Override
	public PreparedStatement makeStatement(Connection connection) throws ClassNotFoundException, SQLException {
		//---------------------------------------------
		// query
		//---------------------------------------------
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users (u_id, name, password) VALUES (?,?,?) \n");
		log.debug("sql:\n" + sb.toString());
	
		//---------------------------------------------
		// 실행
		//---------------------------------------------
		PreparedStatement ps = connection.prepareStatement(sb.toString());
		ps.setString(1, user.get_u_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
			
		return ps;
	}
	
}
