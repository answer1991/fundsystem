package com.chenjun.service;

import java.io.Serializable;

import com.chenjun.dao.bean.Account;
import com.chenjun.fund.model.AddSelfCheckStatus;
import com.chenjun.fund.model.LoginStatus;

public interface AccountService {
	public Account get(String username, String password);
	public Account get(Serializable accountId);
	public LoginStatus login(String username, String password);
	public boolean register(String username, String password);
	public void update(Account account);
	public AddSelfCheckStatus addSelfCheckFund(Serializable accountId, String dm);
}
