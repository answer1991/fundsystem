package com.chenjun.fund.model;
/**
 * 这个类封装基金拆分信息
 * @author zet
 *
 */
public class Jjcf implements Model{
	String dm;		//代码
	String ggrq;	//公告日期
	String cfrq;	//拆分日期
	String cfqjz;	//拆分前净值
	String cfhjz;	//拆分后净值
	String cfbl;	//拆分比例
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getGgrq() {
		return ggrq;
	}
	public void setGgrq(String ggrq) {
		this.ggrq = ggrq;
	}
	public String getCfrq() {
		return cfrq;
	}
	public void setCfrq(String cfrq) {
		this.cfrq = cfrq;
	}
	public String getCfqjz() {
		return cfqjz;
	}
	public void setCfqjz(String cfqjz) {
		this.cfqjz = cfqjz;
	}
	public String getCfhjz() {
		return cfhjz;
	}
	public void setCfhjz(String cfhjz) {
		this.cfhjz = cfhjz;
	}
	public String getCfbl() {
		return cfbl;
	}
	public void setCfbl(String cfbl) {
		this.cfbl = cfbl;
	}
	
}
