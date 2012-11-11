package com.chenjun.fund.model;

import java.util.List;
import java.util.TreeMap;

import com.chenjun.fund.drawable.ChartInfo;
import com.chenjun.fund.drawable.ChartPoints;
import com.chenjun.fund.drawable.ValueRange;
import com.chenjun.utils.StringUtils;
/**
 * 这个类封装多个（一段时间内）基金净值的信息.
 * 实现这个类的对象可以作为XY图表点的集合,因此实现XYPointSeries接口
 * @author zet
 *
 */
public class Jjjzs implements Model, ChartPoints{
	private List<Jjjz> jjjzs;		//基金净值队列
	private boolean isSameFund = true;	//是否是同个基金净值信息
	private int count;					//基金净值的个数
	//private ValueRange range;
	
	/**
	 * 构造方法，必须提供多个基金净值的信息（LinkedList<Jjjz>队列）
	 * @param jjjzs LinkedList<Jjjz>类型
	 */
	public Jjjzs(List<Jjjz> jjjzs){
		this.jjjzs = jjjzs;
		count = jjjzs.size();
		checkSameFund();
		//sort();
		//this.range = getJjjzsRange(this.jjjzs.size());
	}
	
	public Jjjz getJjjz(int location){
		if(location > jjjzs.size() -1){
			return null;
		}
		return jjjzs.get(location);
	}
	/**
	 * 返回封装的多个基金净值
	 * @return
	 */
	public List<Jjjz> getJjjzs() {
		return jjjzs;
	}
	
	/**
	 * 返回封装的基金是否为同一个基金
	 * @return boolean,true代表是同一个基金，否则不是同一个
	 */
	public boolean isSameFund() {
		return isSameFund;
	}

	
	/**
	 * 封装的基金个数
	 * @return int
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 对封装的基金信息按时间从小到大排列
	 */
//	private void sort(){
//		Jjjz temp = null;
//		for(int i = 0; i < count; i++){
//			for(int j = i + 1; j < count; j++){
//				if(Integer.parseInt(jjjzs.get(i).getRq()) > Integer.parseInt(jjjzs.get(j).getRq())){
//					temp = jjjzs.get(i);
//					jjjzs.set(i, jjjzs.get(j));
//					jjjzs.set(j, temp);
//				}
//			}
//		}
//	}
	
	/**
	 * 检查是否为同一个基金，不是同一个，将isSameFound置为false
	 */
	private void checkSameFund(){
		String dm = null;
		for(int i = 0; i < count; i ++){
			if(i == 0){
				dm = jjjzs.get(0).getDm();
			}else{
				if(!dm.equals(jjjzs.get(i).getDm())){
					isSameFund = false;
					break;
				}
			}
		}
	}
	
	
	public int search(String date){
		int dateInt = Integer.valueOf(date);
		return search(dateInt, 0, jjjzs.size() -1);
	}
	
	private int search(int date, int from, int end){
		if(jjjzs.size() == 0){
			return 0;
		}
		
		if(from == (end - 1)){
			int tempDate = Integer.valueOf(jjjzs.get(from).getRq());
			if(tempDate == date){
				return from;
			}
			else 
				return end;
		}
		
		int half = (end - from) / 2 + from;
		
		int tempDate = Integer.valueOf(jjjzs.get(half).getRq());
//		System.out.println(from);
//		System.out.println(tempDate);
//		System.out.println(end);
		if(tempDate == date){
			return half;
		}
		else if(tempDate > date){
			return search(date, from, half);
		}
		else{
			return search(date, half, end);
		}
	}
	
	/**
	 * 获得基金净值的区间
	 */
	public ValueRange getJjjzsRange(int count){
		double min = 0;				//Y轴最小值
		double max = 0;				//Y轴最大值
		int pointCount = getPointCount(count);
		int offset= this.count - 1;
		for(int i = 0; i < pointCount; i++){
			//System.out.println(jjjzs.get(i).getRq());
			if(i == 0){
				min = jjjzs.get(offset).getJzForPoint();
			}
			if(jjjzs.get(offset).getJzForPoint() < min){
				min = jjjzs.get(offset).getJzForPoint();
			}
			if(jjjzs.get(offset).getJzForPoint() > max){
				max = jjjzs.get(offset).getJzForPoint();
			}
			offset -= 1;
		}
		return new ValueRange(min, max);
	}
	
	/**
	 * 获得基金净值的区间
	 */
	public ValueRange getJjjzsRange(int from, int end){
		double min = 0;				//Y轴最小值
		double max = 0;				//Y轴最大值
		//int pointCount = getPointCount(end - from);
		
//		int offset ;
//		if(end >  this.count){
//			offset = this.count - 1;
//		} else { 
//			offset = end - 1;
//		}
		
		for(int i = from; i < end; i++){
			//System.out.println(jjjzs.get(i).getRq());
			if(i == from){
				min = jjjzs.get(i).getJzForPoint();
			}
			if(jjjzs.get(i).getJzForPoint() < min){
				min = jjjzs.get(i).getJzForPoint();
			}
			if(jjjzs.get(i).getJzForPoint() > max){
				max = jjjzs.get(i).getJzForPoint();
			}
			//offset -= 1;
		}
		
//		System.out.println(min);
//		System.out.println(max);
		return new ValueRange(min, max);
	}
	
	
	/**
	 * 返回X轴的长度
	 * @param count 要显示多少天内的基金
	 * @return Range类型
	 */
	public int getPointCount(int count) {
		if(this.count < count){
			return this.count;
		}
		else{
			return count;
		}
	}
	
	
	/**
	 * 实现ChartPoints接口的方法，返回图表的信息
	 * @param count 表示图表中要显示多少天内的基金，如果大于已拥有的净值走势天数，则返回所有
	 * @return 图表信息
	 */
	@Override
	public ChartInfo getChartInfo(int count) {
		ChartInfo chartInfo = new ChartInfo();
		TreeMap<Integer, Double> pointMap = new TreeMap<Integer, Double>(); 
		TreeMap<Integer, String> pointDomainMap = new TreeMap<Integer, String>(); 
		TreeMap<Integer, Object> extraInfoMap = new TreeMap<Integer, Object>();
		int pointCount = getPointCount(count);
		ValueRange jjjzsRange = this.getJjjzsRange(count);
		int yOffset = this.count - 1;
		int xOffset = pointCount -1;
		
		String[] valueTick = getValueTick(jjjzsRange);
		
		String domainMax = jjjzs.get(yOffset).getRq();
		String domainHalf = jjjzs.get((yOffset - xOffset) + (pointCount / 2)).getRq();
		String domainMin = jjjzs.get(yOffset - xOffset).getRq();
		String[] domainTick = new String[]{domainMin, domainHalf, domainMax}; 
		domainTick = StringUtils.dateStringArrayChange(domainTick);
		
		for(int i = 0; i < pointCount; i++){
			pointMap.put(xOffset, jjjzs.get(yOffset).getJzForPoint());
			pointDomainMap.put(xOffset, jjjzs.get(yOffset).getRq());
			extraInfoMap.put(xOffset, jjjzs.get(yOffset));
			//System.out.println(xOffset + "--->" + jjjzs.get(yOffset).getY());
			yOffset -= 1;
			xOffset -= 1;
		}
		chartInfo.setPoint(pointMap);
		chartInfo.setPointDomainInfo(pointDomainMap);
		chartInfo.setPointExtreInfo(extraInfoMap);
		chartInfo.setPiontCount(pointCount);
		chartInfo.setDomainCount(count);
		chartInfo.setValueRange(jjjzsRange);
		chartInfo.setValueTick(valueTick);
		chartInfo.setDomainTick(domainTick);
		/*
		for (String string : valueTick) {
			System.out.println(string);
		}
		for (String string : domainTick) {
			System.out.println(string);
		}*/
		return chartInfo;
	}

	/**
	 * 这个方法暂时用不上
	 */
	@Override
	public ChartInfo getChartInfo(int from, int end) {
		if(from > this.count -1){
			return null;
		}
		
		ChartInfo chartInfo = new ChartInfo();
		TreeMap<Integer, Double> pointMap = new TreeMap<Integer, Double>(); 
		TreeMap<Integer, String> pointDomainMap = new TreeMap<Integer, String>(); 
		TreeMap<Integer, Object> extraInfoMap = new TreeMap<Integer, Object>();
		
		int pointCount = getPointCount(end - from);
		ValueRange jjjzsRange = this.getJjjzsRange(from, end);
		int yOffset = end - 1;
		int xOffset = pointCount -1;
		int startOffset = from;
		
		String[] valueTick = getValueTick(jjjzsRange);
		
		String domainMax = jjjzs.get(yOffset).getRq();
		String domainHalf = jjjzs.get((yOffset - xOffset) + (pointCount / 2)).getRq();
		String domainMin = jjjzs.get(yOffset - xOffset).getRq();
		String[] domainTick = new String[]{domainMin, domainHalf, domainMax}; 
		domainTick = StringUtils.dateStringArrayChange(domainTick);
		
		for(int i = 0; i < pointCount; i++){
			pointMap.put(i, jjjzs.get(startOffset).getJzForPoint());
			pointDomainMap.put(i, jjjzs.get(startOffset).getRq());
			extraInfoMap.put(i, jjjzs.get(startOffset));
			//System.out.println(xOffset + "--->" + jjjzs.get(yOffset).getY());
			startOffset++;
		}
		chartInfo.setPoint(pointMap);
		chartInfo.setPointDomainInfo(pointDomainMap);
		chartInfo.setPointExtreInfo(extraInfoMap);
		chartInfo.setPiontCount(pointCount);
		chartInfo.setDomainCount(end - from);
		chartInfo.setValueRange(jjjzsRange);
		chartInfo.setValueTick(valueTick);
		chartInfo.setDomainTick(domainTick);
		/*
		for (String string : valueTick) {
			System.out.println(string);
		}
		for (String string : domainTick) {
			System.out.println(string);
		}*/
		return chartInfo;
	}
	
	/**
	 * 从值的区间获得Y轴的标量，这个函数会产生5个标量
	 * @param range
	 * @return
	 */
	private String[] getValueTick(ValueRange range){
		String valueMin = String.valueOf(range.getMin());
		String valueQuarter = String.valueOf((range.getMax() - range.getMin()) / 4 + range.getMin());
		String valueHalf = String.valueOf((range.getMax() - range.getMin()) / 2 + range.getMin());
		String value3Quarter = String.valueOf((range.getMax() - range.getMin()) *3 / 4 + range.getMin());
		String valueMax = String.valueOf(range.getMax());
		String[] valueTick = new String[]{valueMin, valueQuarter, valueHalf, value3Quarter, valueMax};
		valueTick = StringUtils.stringArrayLengthFormat(valueTick, 6, '0');
		return valueTick;
	}
	
}
