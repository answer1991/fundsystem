package com.chenjun.fund.drawable;

import java.util.TreeMap;

/**
 * 封装一个画出点线图所需要的全部信息
 * 这个图中的每一个点，是X轴自增的。
 * @author zet
 *
 */
public class ChartInfo {
	private TreeMap<Integer, Double> point;				//Y轴对应的值
	private TreeMap<Integer, String> pointDomainInfo;	//X轴对应的信息
	private TreeMap<Integer, Object> pointExtraInfo;	//额外信息
	private int piontCount;								//图表中点的个数
	private ValueRange valueRange;						//Y轴值区域
	private int domainCount;							//X轴点的个数
	private String[] valueTick;							//Y轴标识
	private String[] domainTick;						//X轴标识
	
	public TreeMap<Integer, Object> getPointExtraInfo() {
		return pointExtraInfo;
	}
	public void setPointExtreInfo(
			TreeMap<Integer, Object> pointExtraInfo) {
		this.pointExtraInfo = pointExtraInfo;
	}
	public TreeMap<Integer, String> getPointDomainInfo() {
		return pointDomainInfo;
	}
	public void setPointDomainInfo(TreeMap<Integer, String> pointDomainInfo) {
		this.pointDomainInfo = pointDomainInfo;
	}
	public String[] getValueTick() {
		return valueTick;
	}
	public void setValueTick(String[] valueTick) {
		this.valueTick = valueTick;
	}
	public String[] getDomainTick() {
		return domainTick;
	}
	public void setDomainTick(String[] domainTick) {
		this.domainTick = domainTick;
	}
	public int getPiontCount() {
		return piontCount;
	}
	public void setPiontCount(int piontCount) {
		this.piontCount = piontCount;
	}
	public TreeMap<Integer, Double> getPoint() {
		return point;
	}
	public void setPoint(TreeMap<Integer, Double> point) {
		this.point = point;
	}
	public ValueRange getValueRange() {
		return valueRange;
	}
	public void setValueRange(ValueRange valueRange) {
		this.valueRange = valueRange;
	}
	public int getDomainCount() {
		return domainCount;
	}
	public void setDomainCount(int keyRange) {
		domainCount = keyRange;
	}
}
