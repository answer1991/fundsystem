package com.chenjun.fund.model;
/**
 * 这个类封装基金分红信息
 * @author zet
 *
 */
public class Jjfh implements Model{
	String dm;		//代码
	String ggrq;	//公告日期
	String dwfh;	//单位分红
	String gqdjr;	//股权登记日
	String cxr;		//除息日
	String pxr;		//派息日
	String ztr;		//红利再投日
	String jjfejs;	//基金份额基数
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
	public String getDwfh() {
		return dwfh;
	}
	public void setDwfh(String dwfh) {
		this.dwfh = dwfh;
	}
	public String getGqdjr() {
		return gqdjr;
	}
	public void setGqdjr(String gqdjr) {
		this.gqdjr = gqdjr;
	}
	public String getCxr() {
		return cxr;
	}
	public void setCxr(String cxr) {
		this.cxr = cxr;
	}
	public String getPxr() {
		return pxr;
	}
	public void setPxr(String pxr) {
		this.pxr = pxr;
	}
	public String getZtr() {
		return ztr;
	}
	public void setZtr(String ztr) {
		this.ztr = ztr;
	}
	public String getJjfejs() {
		return jjfejs;
	}
	public void setJjfejs(String jjfejs) {
		this.jjfejs = jjfejs;
	}
	
}
