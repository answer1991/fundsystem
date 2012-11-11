package com.chenjun.fund.model;
/**
 * 这个类封装基金净值的信息
 * @author zet
 *
 */
public class Jjjz implements Model{
	private String dm;			//代码
	private String rq;			//日期
	private String jz;			//净值
	private String ljjz;		//累计净值
	private String fqjz;		//复权净值
	
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
	public String getFqjz() {
		return fqjz;
	}
	public void setFqjz(String fqjz) {
		this.fqjz = fqjz;
	}
}
