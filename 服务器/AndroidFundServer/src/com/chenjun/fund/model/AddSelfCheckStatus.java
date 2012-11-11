package com.chenjun.fund.model;

import java.util.List;

public class AddSelfCheckStatus {
	private boolean isSuccess;
	private List<String> dms;
	
	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public List<String> getDms() {
		return dms;
	}
	public void setDms(List<String> dms) {
		this.dms = dms;
	}
	
	
}
