package com.chenjun.fund.model;


/**
 * 封装一个简单的基金概况，为基金基本信息提供概况信息
 * @author zet
 *
 */
public class SimpleJjgk {
	private String dm;				//代码
	private String jjjc;			//基金简称
	private String tzlx;			//投资类型
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getJjjc() {
		return jjjc;
	}
	public void setJjjc(String jjjc) {
		this.jjjc = jjjc;
	}
	public String getTzlx() {
		return tzlx;
	}
	public void setTzlx(String tzlx) {
		this.tzlx = tzlx;
	}
	
	
}
