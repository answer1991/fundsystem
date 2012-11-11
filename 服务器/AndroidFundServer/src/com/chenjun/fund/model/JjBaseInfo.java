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
	private String lastDayJz;			//最后一天的净值
	private String lastDayLjjz;			//最后一天的累计净值
	private String lastDayFqjz;			//最后一天的累积净值
	private String rq;					//最后一天的日期
	private String preDayJz;			//最后第二天的净值
	private String preDayFqjz;			//最后第二天的复权净值
	
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
	public String getLastDayJz() {
		return lastDayJz;
	}
	public void setLastDayJz(String lastDayJz) {
		this.lastDayJz = lastDayJz;
	}
	public String getLastDayLjjz() {
		return lastDayLjjz;
	}
	public void setLastDayLjjz(String lastDayLjjz) {
		this.lastDayLjjz = lastDayLjjz;
	}
	public String getLastDayFqjz() {
		return lastDayFqjz;
	}
	public void setLastDayFqjz(String lastDayFqjz) {
		this.lastDayFqjz = lastDayFqjz;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getPreDayJz() {
		return preDayJz;
	}
	public void setPreDayJz(String preDayJz) {
		this.preDayJz = preDayJz;
	}
	public String getPreDayFqjz() {
		return preDayFqjz;
	}
	public void setPreDayFqjz(String preDayFqjz) {
		this.preDayFqjz = preDayFqjz;
	}
	
	
	
}
