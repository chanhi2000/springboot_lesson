package com.sist1.hr12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public abstract PreparedStatement makeStatement(Connection connection) throws ClassNotFoundException, SQLException;
}
