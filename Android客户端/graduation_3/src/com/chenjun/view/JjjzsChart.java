package com.chenjun.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.chenjun.fund.drawable.ChartInfo;
import com.chenjun.fund.drawable.DrawSizeInfo;
import com.chenjun.fund.drawable.Drawable;
import com.chenjun.fund.model.Jjjz;
import com.chenjun.fund.model.Jjjzs;
import com.chenjun.utils.DateUtils;
import com.chenjun.utils.StringUtils;

public class JjjzsChart extends View{
	/**
	 * 图表滑动时的优先级控制
	 * 如果X轴方向滑动距离超过这个数，就视作是X方向的滑动，Y轴的滑动无效
	 * 反之，如果小于这个数，就视作Y轴的滑动
	 */
	private static final int X_FLING_DISTANCE = 10;
	
	/**
	 * 图表一次滑动动作对图表信息点位置的影响数
	 * 例如，向左滑动一次，startOffset 和 endOffset 都相应的增加这个数。（不溢出的条件下）
	 */
	private static final int ONCE_FLING_CHANGE_OFFSET = 10;
	
	//基金历史净值信息
	private Jjjzs jjjzs;
	
	//图表的类型，初始化为 一月型
	private ChartType chartType = ChartType.MONTH;
	
	//图表的图表信息（点，线，XY轴的参数等等）
	private ChartInfo chartInfo;
	
	//图表的大小
	private DrawSizeInfo sizeInfo;
	
	//统计信息控件组
	private RangeInfoViewGroup rangeInfoViewGroup;
	
	//“图表的图表信息” 相对于 “基金历史净值信息” 的偏移量对应关系
	private int startOffset = 0;
	private int endOffset = 0;
	
	//用户单击的某点在chartInfo中的ID
	private int checkedID;
	
	//表示X的坐标点的个数和图标点的个数相同
	private boolean isScroll = false;
	
	//表示用户是否单击了图标中某点
	private boolean isChecked;
//	float touchedX;
//	float touchedY;
	
	public JjjzsChart(Context context){
		super(context);
		//setWillNotDraw(false);
	}
	
	public JjjzsChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		//setWillNotDraw(false);
	}
	
	
	public ChartType getChartType() {
		return chartType;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public Jjjzs getJjjzs() {
		return jjjzs;
	}
	public void setJjjzs(Jjjzs jjjzs) {
		this.jjjzs = jjjzs;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public RangeInfoViewGroup getRangeInfoViewGroup() {
		return rangeInfoViewGroup;
	}
	public void setRangeInfoViewGroup(RangeInfoViewGroup rangeInfoViewGroup) {
		this.rangeInfoViewGroup = rangeInfoViewGroup;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		synchronized(this){
			super.onDraw(canvas);
			
			//初始化“图表信息（ChartInfo）”
			initChartInfo();
			
			//画折线图
			sizeInfo = new DrawSizeInfo(getWidth(), getHeight(), getWidth() * 0.1, getHeight() * 0.1, getWidth() * 0.03, getHeight() * 0.1 );
			Drawable.drawPointLineChart(canvas, chartInfo, sizeInfo, isScroll);
			
			//画出额外信息（图表上的当天净值和涨跌幅信息）
			drawExtraInfo(canvas);
			
			//画用户单击图表的信息
			drawTouchLine(canvas);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(chartInfo == null){
			return false;
		}
		return gestureDetector.onTouchEvent(event);
	}
	
	/**
	 * 初始化“图表信息（ChartInfo）”
	 * 如果“基金历史净值信息（Jjjzs）”不为空，那么将从“基金历史净值信息（Jjjzs）”里获得画图的“图表信息（ChartInfo）”，再来画图表的折线图
	 */
	private void initChartInfo(){
		if(jjjzs != null){
			switch(chartType){
			case TEN_DAY:
				endOffset = jjjzs.getCount();
				startOffset = jjjzs.search(DateUtils.getPre10DayStr());
				break;
			case MONTH:
				endOffset = jjjzs.getCount();
				startOffset = jjjzs.search(DateUtils.getPreMonthDayStr());
				break;
			case THREE_MONTH:
				endOffset = jjjzs.getCount();
				startOffset = jjjzs.search(DateUtils.getPre3MonthDayStr());
				break;
			case HALF_YEAR:
				endOffset = jjjzs.getCount();
				startOffset = jjjzs.search(DateUtils.getPreHalfYearDayStr());
				break;
			case YEAR:
				endOffset = jjjzs.getCount();
				startOffset = jjjzs.search(DateUtils.getPreYearDayStr());
				break;
			case SELF_CONFIG:
				break;
			default:
				break;
			}
			chartInfo = jjjzs.getChartInfo(startOffset, endOffset);
			this.refleshRangeInfo();
		}
	}
	
	/**
	 * 调用这个函数能画出用户点击时的辅助线
	 * @param canvas
	 */
	private void drawTouchLine(Canvas canvas){
		if(!isChecked){
			return;
		}
		Paint touchLinePaint = new Paint();
		touchLinePaint.setStyle(Style.STROKE);
		touchLinePaint.setColor(Color.BLUE);
		touchLinePaint.setPathEffect(new DashPathEffect(new float[]{10,5,10,5},1));
		canvas.drawLine(IDtoX(checkedID), (float)(getHeight() * 0.1), IDtoX(checkedID), (float)(getHeight() * 0.9), touchLinePaint);
	}
	
	/**
	 * 当用户单击图表某点时，会调用这个函数，在图表上显示这个点的基金净值信息
	 * 当没有点击或者点击到不合法的图表区域时，会显示最后一天的基金净值信息
	 * @param canvas
	 */
	private void drawExtraInfo(Canvas canvas){
		/*------ 画额外信息-----*/
		Paint extraInfoPaint = new Paint();
        extraInfoPaint.setStyle(Style.FILL);
        extraInfoPaint.setColor(Color.WHITE);
        extraInfoPaint.setTextAlign(Align.LEFT);
        extraInfoPaint.setTypeface(Typeface.DEFAULT);
        extraInfoPaint.setSubpixelText(true);
        extraInfoPaint.setAntiAlias(true);
        extraInfoPaint.setDither(true);
        extraInfoPaint.setTextSize((float)sizeInfo.getPaddingUp() / 2);
		if(chartInfo == null){
			canvas.drawText("正在请求数据..", (float)sizeInfo.getPaddingLeft(), (float)sizeInfo.getPaddingUp() * 3 / 4, extraInfoPaint);
			return;
		}
		if(!isChecked){
			checkedID = chartInfo.getPiontCount() - 1;
		}
		if(checkedID == 0){
			Jjjz jjjz = (Jjjz)chartInfo.getPointExtraInfo().get(checkedID);
        	String info = "日期: " + StringUtils.dateStringChange(jjjz.getRq()) + "  净值: " + StringUtils.stringLenthFormat(jjjz.getJz(), 6, '0');
        	canvas.drawText(info, (float)sizeInfo.getPaddingLeft(), (float)sizeInfo.getPaddingUp() * 3 / 4, extraInfoPaint);
		}else{
			Jjjz jjjz = (Jjjz)chartInfo.getPointExtraInfo().get(checkedID);
			Jjjz preJjjz = (Jjjz)chartInfo.getPointExtraInfo().get(checkedID - 1);
			double jzIncrease = Double.parseDouble(jjjz.getFqjz()) - Double.parseDouble(preJjjz.getFqjz());
			String jzIncreaseStr;
			if(jzIncrease < 0){
				jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 7, '0');
			}else {
				jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 6, '0');
			}
        	String info = "日期: " + StringUtils.dateStringChange(jjjz.getRq()) + "  净值: " + StringUtils.stringLenthFormat(jjjz.getJz(), 6, '0') + " 涨跌:" + jzIncreaseStr;
        	canvas.drawText(info, (float)sizeInfo.getPaddingLeft(), (float)sizeInfo.getPaddingUp() * 3 / 4, extraInfoPaint);
		}
	}
	
	/**
	 * 更新区间统计信息
	 * 当图表中的信息发生变化时，就会调用这个函数，让统计区的信息也发生对应的变化
	 */
	private void refleshRangeInfo(){
		if(rangeInfoViewGroup == null){
			return;
		}
		String startDate = ((Jjjz)chartInfo.getPointExtraInfo().get(0)).getRq();
		String startDateStr = StringUtils.dateStringChange(startDate);
		
		String endDate = ((Jjjz)chartInfo.getPointExtraInfo().get(chartInfo.getPiontCount() - 1)).getRq();
		String endDateStr = StringUtils.dateStringChange(endDate);
		
		double startJz = chartInfo.getPoint().get(0);
		double endJz = chartInfo.getPoint().get(chartInfo.getPiontCount() - 1);
		
		String stratJzStr = StringUtils.stringLenthFormat(String.valueOf(startJz), 6, '0');
		String endJzStr = StringUtils.stringLenthFormat(String.valueOf(endJz), 6, '0');
		
		double startFqjz = Double.parseDouble(((Jjjz)chartInfo.getPointExtraInfo().get(0)).getFqjz());
		double endFqjz = Double.parseDouble(((Jjjz)chartInfo.getPointExtraInfo().get(chartInfo.getPiontCount() - 1)).getFqjz());
		double jzIncrease = endFqjz - startFqjz;
		double jzIncreasePrecent = jzIncrease / startFqjz;
		
		rangeInfoViewGroup.getStartDateTextView().setText(startDateStr);
		rangeInfoViewGroup.getEndDateTextView().setText(endDateStr);
		rangeInfoViewGroup.getStartJjTextView().setText(stratJzStr);
		rangeInfoViewGroup.getEndJjTextView().setText(endJzStr);
		if(jzIncrease < 0){
			String jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 7, '0');
			String jzIncrsesePrecentStr = StringUtils.stringLenthFormat(String.valueOf(jzIncreasePrecent * 100), 6, '0') + "%";
			rangeInfoViewGroup.getRangeIncreaseTextView().setTextColor(Color.GREEN);
			rangeInfoViewGroup.getRangeIncreaseTextView().setText(jzIncreaseStr);
			rangeInfoViewGroup.getRangeIncreasePrecentTextView().setTextColor(Color.GREEN);
			rangeInfoViewGroup.getRangeIncreasePrecentTextView().setText(jzIncrsesePrecentStr);
		}else{
			String jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 6, '0');
			String jzIncrsesePrecentStr = StringUtils.stringLenthFormat(String.valueOf(jzIncreasePrecent * 100), 5, '0') + "%";
			rangeInfoViewGroup.getRangeIncreaseTextView().setTextColor(Color.RED);
			rangeInfoViewGroup.getRangeIncreaseTextView().setText(jzIncreaseStr);
			rangeInfoViewGroup.getRangeIncreasePrecentTextView().setTextColor(Color.RED);
			rangeInfoViewGroup.getRangeIncreasePrecentTextView().setText(jzIncrsesePrecentStr);
		}
	}
	
	/**
	 * 手势监听器
	 */
	private GestureDetector gestureDetector = new GestureDetector(new OnGestureListener()  
    {  
        /**
         * 用户点击图表的动作，这里只考虑点击在了图表的“图”区域外，这个动作将取消以前的单击图表动作
         */
        public boolean onDown(MotionEvent event) {  
        	if(event.getX() < sizeInfo.getPaddingLeft() || event.getX() > (sizeInfo.getWidth() - getPaddingRight())){
        		//System.out.println(event.getX());
        		//System.out.println(sizeInfo.getWidth() - getPaddingRight());
        		JjjzsChart.this.postInvalidate();
        		isChecked = false;
        		return false;
        	}
            return true;  
        }  
        
        /**
         * 有加速度的滑动动作，图表只监听有加速度的滑动动作，没加速度的滑动都无效
         * 得到滑动的X距离，左右滑动优先级高于上下。
         * 如果滑动动作的左右间距像素超过某个值（X_FLING_DISTANCE），那么就算这个滑动动作是左右滑动，忽略上下滑动。
         */
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,    
                float velocityY) {  
        	
        	// 用户按下触摸屏、快速移动后松开,这个时候，你的手指运动是有加速度的。  
            // 由1个MotionEvent ACTION_DOWN,    
            // 多个ACTION_MOVE, 1个ACTION_UP触发    
            // e1：第1个ACTION_DOWN MotionEvent    
            // e2：最后一个ACTION_MOVE MotionEvent    
            // velocityX：X轴上的移动速度，像素/秒    
            // velocityY：Y轴上的移动速度，像素/秒   
        	
        	//先将图表是否被单击“取消”
        	isChecked = false;
        	
        	//得到滑动的X距离，左右滑动优先级高于上下。
        	//如果滑动动作的左右间距像素超过某个值（X_FLING_DISTANCE），那么就算这个滑动动作是左右滑动，忽略上下滑动。
        	float distanceX = e2.getX() - e1.getX();
        	
        	//从左到右滑动
        	if(distanceX > X_FLING_DISTANCE) {
        		return JjjzsChart.this.scrollToRight();
        	}
        	
        	//从右到左滑动
        	else if(distanceX < - X_FLING_DISTANCE) {
        		return JjjzsChart.this.scrollToLeft();
        	}
        	
        	//从上向下滑动
        	if(velocityY > 10){
        		return JjjzsChart.this.scrollToBottom();
        	}
        	
        	//从下向上滑动
        	else if(velocityY < -10){
        		return JjjzsChart.this.scrollToUp();
        	}
            return true;  
        }  
        
        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发    
        public void onLongPress(MotionEvent event) {  
//        	System.out.println("on long pressed");  
        }  
        
        /**
         * 无加速度的滑动，都视作无效动作
         */
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,    
                float distanceY) { 
        	
        	// 滚动事件，当在触摸屏上迅速的移动，会产生onScroll。由ACTION_MOVE产生  
            // e1：第1个ACTION_DOWN MotionEvent  
            // e2：最后一个ACTION_MOVE MotionEvent    
            // distanceX：距离上次产生onScroll事件后，X抽移动的距离  
            // distanceY：距离上次产生onScroll事件后，Y抽移动的距离  
        	
            return true;  
        }  
        
          
        public void onShowPress(MotionEvent event) {  
        	//点击了触摸屏，但是没有移动和弹起的动作。onShowPress和onDown的区别在于  
            //onDown是，一旦触摸屏按下，就马上产生onDown事件，但是onShowPress是onDown事件产生后，  
            //一段时间内，如果没有移动鼠标和弹起事件，就认为是onShowPress事件。
        }  
        
        /**
         * 单击图表动作，程序这里已经将没有点到“图”的区域内的动作给排除（onDown()返回false），
         * 只要考虑点击在“图”内的方法
         */
        public boolean onSingleTapUp(MotionEvent event) {  
        	// 轻击触摸屏后，弹起。如果这个过程中产生了onLongPress、onScroll和onFling事件，就不会产生onSingleTapUp事件。
        	//得到触摸点的X坐标
            float tapUpX = event.getX();
            //将ID坐标关联到最近的图表点上
            checkedID = touchXtoID(tapUpX);
            //设置图表是否被点击为“真”
            isChecked = true;
            JjjzsChart.this.postInvalidate();
            return true;  
        }  
          
    });  
	
	/**
	 * 图表触控，从右向左滑动
	 * @return
	 */
	private boolean scrollToLeft(){
		startOffset += ONCE_FLING_CHANGE_OFFSET;
		endOffset += ONCE_FLING_CHANGE_OFFSET;
		
		JjjzsChart.this.validateOffset();
		chartType = ChartType.SELF_CONFIG;
		JjjzsChart.this.postInvalidate();
		return true;
	}
	
	
	/**
	 * 图表触控，从左向右滑动
	 * @return
	 */
	private boolean scrollToRight(){
		startOffset -= ONCE_FLING_CHANGE_OFFSET;
		endOffset -= ONCE_FLING_CHANGE_OFFSET;
		JjjzsChart.this.validateOffset();
		chartType = ChartType.SELF_CONFIG;
		JjjzsChart.this.postInvalidate();
		return true;
	}
	
	/**
	 * 图表触控，从下向上滑动
	 * @return
	 */
	private boolean scrollToUp(){
		startOffset -= ONCE_FLING_CHANGE_OFFSET;
		JjjzsChart.this.validateOffset();
		chartType = ChartType.SELF_CONFIG;
		JjjzsChart.this.postInvalidate();
		return true;
	}
	
	/**
	 * 图表触控，从上向下滑动
	 * @return
	 */
	private boolean scrollToBottom(){
		startOffset += ONCE_FLING_CHANGE_OFFSET;
		JjjzsChart.this.validateOffset();
		chartType = ChartType.SELF_CONFIG;
		JjjzsChart.this.postInvalidate();
		return true;
	}
	
	/**
	 * 验证表数据的起始点和终止点，以防止数据溢出
	 */
	private void validateOffset(){
		if(endOffset < 1){
			endOffset = 1;
		}
		if(endOffset > jjjzs.getCount()){
			endOffset = jjjzs.getCount();
		}
		
		if(startOffset < 0){
			startOffset = 0;
		}
		if(startOffset > endOffset - 1){
			startOffset = endOffset -1;
		}
		
	}
	
	/**
	 * 将点击点的坐标转换为对应的基金在ChartPoints中的下标
	 * 这两个的转化并不是多此一举，用户点击获得的X下可能不是基金图表的某一点，通过这两个函数的转化能获得离这个点击点的最近的基金信息
	 * @param touchX
	 * @return
	 */
	private int touchXtoID(float touchX){
		double cellX;
		double chartWidth = sizeInfo.getWidth() - sizeInfo.getPaddingLeft() - sizeInfo.getPaddingRight();
		if(isScroll){
			cellX = (double)chartWidth / (chartInfo.getDomainCount() -1);
		}else{
			cellX = (double)chartWidth / (chartInfo.getPiontCount() -1);
		}
		return (int)((touchX - sizeInfo.getPaddingLeft()) / cellX);
	}
	
	
	/**
	 * 将基金的下标转化为JjjzsChart中的X坐标
	 * @param ID
	 * @return
	 */
	private float IDtoX(int ID){
		//float cellX = (float)(sizeInfo.getWidth() - sizeInfo.getPaddingLeft() - sizeInfo.getPaddingRight()) / (chartInfo.getDomainCount() -1);
		double cellX;
		double chartWidth = sizeInfo.getWidth() - sizeInfo.getPaddingLeft() - sizeInfo.getPaddingRight();
		if(isScroll){
			cellX = (double)chartWidth / (chartInfo.getDomainCount() -1);
		}else{
			cellX = (double)chartWidth / (chartInfo.getPiontCount() -1);
		}
		return (float)(ID * cellX + sizeInfo.getPaddingLeft());
	}
	
	/**
	 * 图表中的图的类型，根据显示基金净值的天数分类
	 * 有 十天型，一月型，一季度型，半年型，一年型，和自定义
	 * 初始化时是一月型
	 * 自定义型是用户滑动了图表之后的类型。
	 * @author zet
	 *
	 */
	public enum ChartType{
		TEN_DAY,
		MONTH,
		THREE_MONTH,
		HALF_YEAR,
		YEAR,
		SELF_CONFIG;
	}
}
