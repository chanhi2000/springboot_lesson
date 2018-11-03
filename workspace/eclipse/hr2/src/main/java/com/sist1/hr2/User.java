package com.sist1.hr2;

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
	
	public User() {}
	
	public User(String u_id, 
				String name, 
				String password) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
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
	
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + "]";
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
		if (this == obj)							return true;
		if (obj == null)							return false;
		if (getClass() != obj.getClass())			return false;
		User other = (User) obj;
		if (name == null && other.name != null) {	return false;  } 
		else if (!name.equals(other.name))		{	return false;  }
		if (password == null && other.password != null) {	return false;  }
		else if (!password.equals(other.password))		{  return false;  }
		if (u_id == null && other.u_id != null) {	return false;  }
		else if (!u_id.equals(other.u_id))	{	return false;	}
		return true;
	}
}
