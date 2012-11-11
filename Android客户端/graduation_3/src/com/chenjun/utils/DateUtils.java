package com.chenjun.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMdd");
	private static Date today;
	private static Calendar cal;
	
	static {
		today = new Date("12/13/2011");
		cal = Calendar.getInstance();
	}
	
	public static String getTodayDateStr(){
		return DATEFORMAT.format(new Date());
	}
	
	public static String getPre10DayStr(){
		cal.setTime(today);
		cal.add(Calendar.DATE, -10);
		
		return DATEFORMAT.format(cal.getTime());
	}
	
	public static String getPreMonthDayStr(){
		cal.setTime(today);
		cal.add(Calendar.MONTH, -1);
		
		return DATEFORMAT.format(cal.getTime());
	}
	
	public static String getPre3MonthDayStr(){
		cal.setTime(today);
		cal.add(Calendar.MONTH, -3);
		
		return DATEFORMAT.format(cal.getTime());
	}
	
	public static String getPreHalfYearDayStr(){
		cal.setTime(today);
		cal.add(Calendar.MONTH, -6);
		
		return DATEFORMAT.format(cal.getTime());
	}
	
	public static String getPreYearDayStr(){
		cal.setTime(today);
		cal.add(Calendar.YEAR, -1);
		
		return DATEFORMAT.format(cal.getTime());
	}
}
