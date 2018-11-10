package com.sist1.hr11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcContext {
	private Logger log = Logger.getLogger(this.getClass());
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void workWIthStatementStrategy(StatementStrategy strategy) throws ClassNotFoundException, SQLException {
		//---------------------------------------------
		// DB 연결
		//--------------------------------------------- 
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dataSource.getConnection();
			ps = strategy.makeStatement(c);
								
			int flag = ps.executeUpdate();
			log.debug("flag:\n"+flag);
		} catch (SQLException e) {
			throw e;
		} finally {
			//---------------------------------------------
			// 후 실행
			//---------------------------------------------
			if (ps!= null) {
				try {
					ps.close();
				} catch (SQLException e) {
								
				}
			}
			if (c != null) {  
				c.close();  
			}
		}
	}
}
