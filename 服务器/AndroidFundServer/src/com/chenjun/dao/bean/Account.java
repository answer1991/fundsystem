package com.chenjun.dao.bean;

import java.util.Set;

public class Account {
	private String id;
	private String username;
	private String password;
	private Set<SelfCheckJj> selfCheckJjs;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String account) {
		this.username = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<SelfCheckJj> getSelfCheckJjs() {
		return selfCheckJjs;
	}
	public void setSelfCheckJjs(Set<SelfCheckJj> selfCheckJjs) {
		this.selfCheckJjs = selfCheckJjs;
	}
	
}
