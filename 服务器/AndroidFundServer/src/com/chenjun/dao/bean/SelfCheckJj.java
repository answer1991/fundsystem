package com.chenjun.dao.bean;

public class SelfCheckJj {
	private String id;
	private String dm;
	private Account account;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dm == null) ? 0 : dm.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelfCheckJj other = (SelfCheckJj) obj;
		if (dm == null) {
			if (other.dm != null)
				return false;
		} else if (!dm.equals(other.dm))
			return false;
		return true;
	}
	
	
}
