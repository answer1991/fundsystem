package com.chenjun.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.chenjun.dao.AccountDao;
import com.chenjun.dao.bean.Account;
import com.chenjun.dao.bean.SelfCheckJj;
import com.chenjun.dao.impl.AccountDaoImpl;
import com.chenjun.fund.model.AddSelfCheckStatus;
import com.chenjun.fund.model.LoginStatus;
import com.chenjun.service.AccountService;

public class AccountServiceImpl implements AccountService{
	
	private static final String ERROR_INFO = "登陆失败，用户名或者密码错误";
	private static final String SUCCESS_INFO = "登陆成功，欢迎回来";
	
	private AccountDao accountDao = new AccountDaoImpl();
	
	/**
	 * 根据ID获得具体账户
	 */
	public Account get(Serializable accountId) {
		return accountDao.get(accountId);
	}
	
	/**
	 * 根据用户名和密码获得账户
	 * 如果账户不存在，则返回null
	 */
	public Account get(String username, String password) {
		return accountDao.get(username, password);
	}
	
	
	/**
	 * 根据用户名和密码登录
	 * 返回登录状况对象
	 */
	public LoginStatus login(String username, String password) {
		LoginStatus loginStatus = new LoginStatus();
		Account accountC = accountDao.get(username, password);
		
		if(null == accountC){
			loginStatus.setSussess(false);
			loginStatus.setExtraInfo(ERROR_INFO);
		}
		else{
			loginStatus.setSussess(true);
			loginStatus.setExtraInfo(SUCCESS_INFO);
			loginStatus.setAccountId(accountC.getId());
			
			List<String> list = new ArrayList<String>();
			for(Iterator<SelfCheckJj> it = accountC.getSelfCheckJjs().iterator(); it.hasNext();){
				list.add(it.next().getDm());
			}
			
			loginStatus.setSelfCheckJjDms(list);
		}
		
		return loginStatus;
	}
	
	/**
	 * 根据用户名和密码注册
	 * 如果注册成功，返回true
	 * 注册失败返回false，失败多由账户名已存在造成
	 */
	public boolean register(String account, String password) {
		return accountDao.register(account, password);
	}
	
	/**
	 * 更新账户信息
	 */
	public void update(Account account) {
		accountDao.update(account);
	}
	
	/**
	 * 为账户加入某个自选基金
	 */
	public AddSelfCheckStatus addSelfCheckFund(Serializable accountId, String dm) {
		AddSelfCheckStatus status = new AddSelfCheckStatus();
		Account account = get(accountId);
		
		if(account == null){
			status.setSuccess(false);
			return status;
		}
		
		SelfCheckJj selfCheckJj = new SelfCheckJj();
		selfCheckJj.setAccount(account);
		selfCheckJj.setDm(dm);
		
		account.getSelfCheckJjs().add(selfCheckJj);
		
		update(account);
		
		status.setSuccess(true);
		
		List<String> list = new ArrayList<String>();
		for(Iterator<SelfCheckJj> it = account.getSelfCheckJjs().iterator(); it.hasNext();){
			list.add(it.next().getDm());
		}
		status.setDms(list);
		
		return status;
	}
}
