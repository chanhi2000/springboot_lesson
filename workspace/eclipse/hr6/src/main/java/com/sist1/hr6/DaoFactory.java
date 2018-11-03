package com.sist1.hr6;

public class DaoFactory {
	/**
	 * DaoFactory
	 * @return UserDao
	 */
	public UserDao userDao() {
        return new UserDao(  connection()  );
	}
	
	private ConnectionMaker connection() {
		return new NConnectionMaker();
	}
}
