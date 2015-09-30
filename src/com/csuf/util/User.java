package com.csuf.util;
import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String uid;
	private String role;
	
	
	public User(String un, String pwd, String id, String rl){
		this.username=un;
		this.password=pwd;
		this.uid=id;
		this.role=rl;
		
	}
	
	public String getadminnmae() {
		return username;
	}
	public void setfirstname(String adminname) {
		this.username = adminname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getuid() {
		return uid;
	}
	public void setuid(String uid) {
		this.uid = uid;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		return "adminname="+this.username+", password="+this.password+", uid="+this.uid+", role="+this.role;
	}

	
	

}

