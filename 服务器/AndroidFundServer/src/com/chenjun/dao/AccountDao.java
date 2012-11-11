package com.chenjun.dao;

import java.io.Serializable;

import com.chenjun.dao.bean.Account;

public interface AccountDao {
	public Account get(Serializable accountId);
	public Account get(String username, String password);
	public boolean register(String username, String password);
	public void update(Account account);
}
