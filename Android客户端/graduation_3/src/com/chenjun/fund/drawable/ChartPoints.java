package com.chenjun.fund.drawable;


/**
 * 图表点的信息集合接口
 * @author zet
 *
 */
public interface ChartPoints {
	public ChartInfo getChartInfo(int count);
	public ChartInfo getChartInfo(int from, int end);
}
