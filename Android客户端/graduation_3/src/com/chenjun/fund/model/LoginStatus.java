package com.chenjun.fund.model;

import java.util.List;

public class LoginStatus {
	private String accountId;
	private boolean isSussess;
	private String extraInfo;
	private List<String> selfCheckJjDms;
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public boolean isSussess() {
		return isSussess;
	}
	public void setSussess(boolean isSussess) {
		this.isSussess = isSussess;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	public List<String> getSelfCheckJjDms() {
		return selfCheckJjDms;
	}
	public void setSelfCheckJjDms(List<String> selfCheckJjDms) {
		this.selfCheckJjDms = selfCheckJjDms;
	}
}
