package com.chenjun.view;

import android.widget.TextView;

/**
 * 这个类会指向净值走势下面的区间统计控件
 * @author zet
 *
 */
public class RangeInfoViewGroup {
	private TextView startDateTextView;
	private TextView endDateTextView;
	private TextView startJjTextView;
	private TextView endJjTextView;
	private TextView rangeIncreaseTextView;
	private TextView rangeIncreasePrecentTextView;
	public TextView getStartDateTextView() {
		return startDateTextView;
	}
	public void setStartDateTextView(TextView startDateTextView) {
		this.startDateTextView = startDateTextView;
	}
	public TextView getEndDateTextView() {
		return endDateTextView;
	}
	public void setEndDateTextView(TextView endDateTextView) {
		this.endDateTextView = endDateTextView;
	}
	public TextView getStartJjTextView() {
		return startJjTextView;
	}
	public void setStartJjTextView(TextView startJjTextView) {
		this.startJjTextView = startJjTextView;
	}
	public TextView getEndJjTextView() {
		return endJjTextView;
	}
	public void setEndJjTextView(TextView endJjTextView) {
		this.endJjTextView = endJjTextView;
	}
	public TextView getRangeIncreaseTextView() {
		return rangeIncreaseTextView;
	}
	public void setRangeIncreaseTextView(TextView rangeIncreaseTextView) {
		this.rangeIncreaseTextView = rangeIncreaseTextView;
	}
	public TextView getRangeIncreasePrecentTextView() {
		return rangeIncreasePrecentTextView;
	}
	public void setRangeIncreasePrecentTextView(
			TextView rangeIncreasePrecentTextView) {
		this.rangeIncreasePrecentTextView = rangeIncreasePrecentTextView;
	}
	
	
}
