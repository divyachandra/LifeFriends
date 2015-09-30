package com.csuf.util;
import java.io.Serializable;

public class Admin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String aid;
	
	
	
	public Admin(String un, String pwd, String id){
		this.username=un;
		this.password=pwd;
		this.aid=id;
		
		
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
		return aid;
	}
	public void setuid(String uid) {
		this.aid = uid;
	}
	
	@Override
	public String toString(){
		return "adminname="+this.username+", password="+this.password+", aid="+this.aid;
	}

	
	

}

