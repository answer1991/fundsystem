package com.chenjun.fund.model;

/**
 * 一个基金的基本信息
 * @author zet
 *
 */
public class JjBaseInfo {
	
	private String dm;					//代码
	private String jjjc;				//基金简称
	private String tzlx;				//投资类型
	private String jz;					//净值
	private String ljjz;				//累计净值
	private String rq;					//日期
	private String increase;			//涨跌
	private String increasePrecent;		//涨跌幅
	
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
	public String getJz() {
		return jz;
	}
	public void setJz(String jz) {
		this.jz = jz;
	}
	public String getLjjz() {
		return ljjz;
	}
	public void setLjjz(String ljjz) {
		this.ljjz = ljjz;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public String getIncreasePrecent() {
		return increasePrecent;
	}
	public void setIncreasePrecent(String increasePrecent) {
		this.increasePrecent = increasePrecent;
	}

	
}
