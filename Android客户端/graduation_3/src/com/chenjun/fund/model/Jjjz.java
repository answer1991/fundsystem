package com.chenjun.fund.model;



/**
 * 这个类封装基金净值的信息。
 * 实现这个类的对象可以作为XY图表的点，因此实现XYPoint接口
 * @author zet
 *
 */
public class Jjjz implements Model{
	String dm;			//代码
	String rq;			//日期
	String jz;			//净值
	String ljjz;		//累计净值
	String fqjz;		//复权净值
	
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
	
	public double getJzForPoint() {
		return Double.parseDouble(jz);
	}
}
