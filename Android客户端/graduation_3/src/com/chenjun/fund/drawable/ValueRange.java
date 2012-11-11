package com.chenjun.fund.drawable;

/**
 * 封装值的区间，画图时根据这个区间来控制单元格的大小
 * @author zet
 *
 */
public class ValueRange {
	double min;
	double max;
	public ValueRange(double min, double max){
		this.min = min;
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
}
