package com.chenjun.fund.drawable;

import java.util.TreeMap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;

/**
 * 画图辅助类
 * 目前就定义一个基金净值走势基本曲线图的画图的静态方法，不提供触控或者滑动的效果画图，滑动和触控的另外画图在基金净值走势图表自己的类(JjjzsChart)里实现
 * @author zet
 *
 */
public class Drawable {
	public static void drawPointLineChart(Canvas canvas, ChartInfo chartInfo, DrawSizeInfo sizeInfo, boolean isScroll){
		if(canvas == null){
			return;
		}
		if(sizeInfo == null){
			return;
		}
		
		double width = sizeInfo.getWidth();
		double height = sizeInfo.getHeight();
		double paddingLeft = sizeInfo.getPaddingLeft();
		double paddingBottom = sizeInfo.getPaddingBottom();
		double paddingRight = sizeInfo.getPaddingRight();
		double paddingUp = sizeInfo.getPaddingBottom();
		double chartWidth = width - paddingLeft - paddingRight;
		double chartHeight = height - paddingUp - paddingBottom;
		
		/* ----   画网格线  -----*/
		
		Paint gridPaint = new Paint();
		gridPaint.setColor(Color.RED);
		gridPaint.setStyle(Paint.Style.STROKE);
		canvas.drawLine((float)paddingLeft, (float)(height - paddingBottom), (float)(width - paddingRight), (float)(height - paddingBottom), gridPaint); //底部线
		canvas.drawLine((float)paddingLeft, (float)paddingUp, (float)(width - paddingRight), (float)paddingUp, gridPaint); //顶端线
        PathEffect effects = new DashPathEffect(new float[]{10,5,10,5},1);  
        gridPaint.setPathEffect(effects);  
        canvas.drawLine((float)paddingLeft, (float)(chartHeight / 2.0 + paddingUp), (float)(width - paddingRight), (float)(chartHeight / 2.0 + paddingUp), gridPaint);  //中线
        PathEffect effects1 = new DashPathEffect(new float[]{5,5,5,5},1);  
        gridPaint.setPathEffect(effects1);  
        canvas.drawLine((float)paddingLeft, (float)(chartHeight / 4.0 + paddingUp), (float)(width - paddingRight), (float)(chartHeight / 4.0 + paddingUp), gridPaint);  //1/4线
        canvas.drawLine((float)paddingLeft, (float)(chartHeight * 3 / 4.0 + paddingUp), (float)(width - paddingRight), (float)(chartHeight * 3 / 4.0 + paddingUp), gridPaint); //3/4线
		if(chartInfo == null){
			Paint tickPaint = new Paint();
			tickPaint.setStyle(Style.STROKE);
			tickPaint.setColor(Color.WHITE);
			tickPaint.setTextAlign(Align.CENTER);
			tickPaint.setSubpixelText(true);
			tickPaint.setTypeface(Typeface.SERIF);
			tickPaint.setTextSize((float)(paddingLeft / 4));
			int valueTickCount = 5;
			double valueTickAddHeight = chartHeight / (valueTickCount - 1);
			tickPaint.setStrokeJoin(Join.ROUND);
			tickPaint.setAntiAlias(true);
			tickPaint.setDither(true);
			for(int i = 0; i < valueTickCount; i++){
	        	canvas.drawText("0.0000", (float)(paddingLeft / 2), (float)(valueTickAddHeight * (valueTickCount - 1 - i) + paddingUp), tickPaint);
	        }
			return;
		}
		String[] valueTick = chartInfo.getValueTick();
		String[] domainTick = chartInfo.getDomainTick();
		
		/*---- 画点与点的连线 -----*/
        //设置画笔颜色  
		Paint linePaint = new Paint(); 
		linePaint.setStyle(Style.STROKE);
		linePaint.setStrokeWidth(2.0f);
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeJoin(Join.ROUND);
		linePaint.setAntiAlias(true);
		linePaint.setDither(true);

		TreeMap<Integer, Double> treeMap = chartInfo.getPoint();
		double cellX ;
		if(isScroll){
			cellX = (double)chartWidth / (chartInfo.getDomainCount() -1);
		}else{
			cellX = (double)chartWidth / (chartInfo.getPiontCount() -1);
		}
		double cellY = (double)chartHeight/ (chartInfo.getValueRange().getMax() - chartInfo.getValueRange().getMin());
		for(int i = 0; i < chartInfo.getPiontCount() - 1; i++){
			float startX = (float) (i * cellX + paddingLeft);
			float startY = (float) ((chartInfo.getValueRange().getMax() - treeMap.get(i)) * cellY + paddingUp);
			float endX = (float) ((i + 1) * cellX + paddingLeft);
			float endY = (float) ((chartInfo.getValueRange().getMax() - treeMap.get(i + 1)) * cellY + paddingUp);
			canvas.drawLine(startX, startY, endX, endY, linePaint);
			//System.out.println("line");
		}
		//canvas.drawText("test", 0, 4, 0, getHeight(), linePaint);
		
        
        /*------ 画Y轴标量 --------*/        
        Paint tickPaint = new Paint();
        tickPaint.setStyle(Style.FILL);
        tickPaint.setColor(Color.WHITE);
        tickPaint.setTextAlign(Align.CENTER);
        tickPaint.setSubpixelText(true);
        tickPaint.setTypeface(Typeface.DEFAULT);
        tickPaint.setTextSize((float)(paddingLeft / 4));
        tickPaint.setAntiAlias(true);
        tickPaint.setDither(true);
        int valueTickCount = valueTick.length;
        double valueTickAddHeight = chartHeight / (valueTickCount - 1);
        for(int i = 0; i < valueTickCount; i++){
        	canvas.drawText(valueTick[i], (float)(paddingLeft / 2), (float)(valueTickAddHeight * (valueTickCount - 1 - i) + paddingUp), tickPaint);
        }
        
        /*------- 画X轴标量 -----*/
        int domainTickCount = domainTick.length;
        tickPaint.setTextSize((float)(paddingBottom / 3));
        double domainTickAddWidth;
        if(isScroll){
        	domainTickAddWidth = (double)((chartInfo.getPiontCount() - 1)) / (chartInfo.getDomainCount() - 1) * chartWidth / (domainTickCount - 1);
        }
        else{
        	domainTickAddWidth = (double)((chartInfo.getPiontCount() - 1)) / (chartInfo.getPiontCount() - 1) * chartWidth / (domainTickCount - 1);
        }
        //System.out.println(domainTickAddWidth);
        for(int i = 0; i < domainTickCount; i++){
        	if(i == 0){
        		tickPaint.setTextAlign(Align.LEFT);
        	}else if(i == domainTickCount -1){
        		tickPaint.setTextAlign(Align.RIGHT);
        	}
        	else{
        		tickPaint.setTextAlign(Align.CENTER);
        	}
        	canvas.drawText(domainTick[i], (float)(i * domainTickAddWidth + paddingLeft), (float)(height - (paddingBottom *2 / 3)), tickPaint);
        }
        
	}
}
