package com.sist1.hr11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DelAllStatement implements StatementStrategy {
	Logger log = Logger.getLogger(this.getClass());

	@Override
	public PreparedStatement makeStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM users");
		return ps;
	}
}
