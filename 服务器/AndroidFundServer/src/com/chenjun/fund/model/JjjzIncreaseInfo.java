package com.chenjun.fund.model;

/**
 * 基金净值涨跌信息
 * @author zet
 *
 */
public class JjjzIncreaseInfo {
	private String dm;					//代码
	private String rq;					//日期
	private String lastDayJz;			//最后一天的净值
	private String lastDayLjjz;			//最后一天的累计净值
	private String lastDayFqjz;			//最后一天的复权净值
	private String preDayJz;			//最后第二天的净值
	private String preDayFqjz;			//最后第二天的复权净值
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
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
