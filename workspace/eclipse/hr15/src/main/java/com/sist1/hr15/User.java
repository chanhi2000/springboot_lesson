package com.sist1.hr15;

/**
 * IP: 211.238.142.102
 * post: 1521
 * SID: orcl
 * sist/1224
 * 
 * users
 * u_id varchar2(10)
 * name varchar2(10)
 * password varchar2(10)
 * 
 * @author sist1
 *
 */

public class User {
	private String u_id;
	private String name;
	private String password;
	private Level hLevel;					// 등급
	private int login;						// 로그인 횟수
	private int recommend;					// 추천 횟수
	private String email;					// 이메일
	private String regDt;					// 등록 날짜
	
	
	public User() {}
	
	public User(String u_id, 
			String name, 
			String password, 
			Level hLevel, 
			int login, 
			int recommend, 
			String email,
			String regDt) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
		this.hLevel = hLevel;
		this.login = login;
		this.recommend = recommend;
		this.email = email;
		this.regDt = regDt;
	}

	public String get_u_id() {
		return u_id;
	}
	public void set_u_id(String u_id) {
		this.u_id = u_id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Level gethLevel() {
		return hLevel;
	}

	public void sethLevel(Level hLevel) {
		this.hLevel = hLevel;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + ", hLevel=" + hLevel + ", login="
				+ login + ", recommend=" + recommend + ", email=" + email + ", regDt=" + regDt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((u_id == null) ? 0 : u_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)					return true;
		if (obj == null)					return false;
		if (getClass() != obj.getClass())	return false;
		
		User other = (User) obj;
		if (name == null && other.name != null) return false;
		else if (!name.equals(other.name))		return false;
		if (password == null && other.password != null) return false;
		else if (!password.equals(other.password))	return false;			
		if (u_id == null && other.u_id != null) 	return false;
		else if (!u_id.equals(other.u_id))			return false;
		return true;
	}
	
	
}
