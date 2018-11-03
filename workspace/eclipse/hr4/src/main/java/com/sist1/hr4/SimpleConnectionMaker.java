package com.sist1.hr4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {
	private static final String url = "jdbc:oracle:thin:@211.238.142.102:1521:orcl";
	protected final String user = "sist";
	protected final String password = "1224";
	
	/**
	 * Oracle Connection 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn; 
	}
}
