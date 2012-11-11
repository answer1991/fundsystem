package com.chenjun.utils;

public class StringUtils {
	
	/**
	 * 统一净值，保留小数点后4位
	 * @param jz
	 * @return
	 */
	public static String jzFormat(String jz){
		if(jz == null){
			return null;
		}
		
		int pointInt = jz.indexOf(".");
		
		return jz.substring(0, pointInt + 5);
	}
	
	/**
	 * 统一涨幅，保留小数点后3位
	 * @param jz
	 * @return
	 */
	public static String increaseFormat(String jz){
		if(jz == null){
			return null;
		}
		
		int pointInt = jz.indexOf(".");
		
		return jz.substring(0, pointInt + 5);
	}
	
	/**
	 * 将源字符串填充到指定的长度并返回。
	 * @param str	源串目标字符串
	 * @param length		目标字符串的长度
	 * @param addChar	填充字符
	 * @return String类型，目标字符串
	 */
	public static String stringLenthFormat(String str, int length, char addChar){
		if(str.length() > length){
			str = str.substring(0, length);
		}
		else if(str.length() < length){
			int addCount = length - str.length();
			for(int i = 0; i < addCount; i ++){
				str = str + addChar;
			}
		}
		return str;
	}
	
	/**
	 * 将源字符串数组的每个字符串填充到对应的长度
	 * @param strArray
	 * @param length
	 * @param addChar
	 * @return
	 */
	public static String[] stringArrayLengthFormat(String[] strArray, int length, char addChar){
		for(int i = 0; i < strArray.length; i++){
			strArray[i] = stringLenthFormat(strArray[i], length, addChar);
		}
		return strArray;
	}
	
	public static boolean isDateString(String dateStr){
		if(dateStr == null){
			return false;
		}
		if(dateStr.length() != 8){
			return false;
		}
		String year = dateStr.substring(0, 4);
		try{
			int yearInt = Integer.parseInt(year);
			if(yearInt > 3000 || yearInt < 1900){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		String month = dateStr.substring(4, 6);
		try{
			int monthInt = Integer.parseInt(month);
			if(monthInt > 13 || monthInt < 1){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		String day = dateStr.substring(6, 8);
		try{
			int dayInt = Integer.parseInt(day);
			if(dayInt > 31 || dayInt < 1){
				return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public static boolean isDateRangeString(String dateRangeStr){
		if(dateRangeStr == null){
			return false;
		}
		if(dateRangeStr.length() != 17){
			return false;
		}
		String startYear = dateRangeStr.substring(0, 8);
		String endYear = dateRangeStr.substring(9, 17);
		if(!isDateString(startYear)){
			return false;
		}
		if(!isDateString(endYear)){
			return false;
		}
		return true;
	}
	
	public static String dateString2zhDateString(String originalStr){
		if(originalStr == null){
			return originalStr;
		}
		if(originalStr.length() != 8){
			return originalStr;
		}
		String year = originalStr.substring(0, 4);
		String month = originalStr.substring(4, 6);
		String day = originalStr.substring(6, 8);
		return year + "年" + month + "月" + day + "日";
	}
	
	public static String dateRangeString2zhDateRangeString(String originalStr){
		if(originalStr == null){
			return originalStr;
		}
		if(originalStr.length() != 17){
			return originalStr;
		}
		String startYear = originalStr.substring(0, 8);
		String endYear = originalStr.substring(9, 17);
		return dateString2zhDateString(startYear) + "-" + dateString2zhDateString(endYear);
	}
	/**
	 * 为给定字符串加两个缩进符
	 */
	public static String addT(String str){
		return "\t\t" + str;
	}
	/**
	 * 将20120102这样的日期格式的字符串转换为2012/01/02
	 * @param originalStr
	 * @return
	 */
	public static String dateStringChange(String originalStr){
		String year = originalStr.substring(0, 4);
		String month = originalStr.substring(4, 6);
		String day = originalStr.substring(6, 8);
		return year + "/" + month + "/" + day;
	}
	
	/**
	 * 将20120102这样的日期格式的字符串数组转换为2012/01/02格式的字符串数组
	 * @param originalStr
	 * @return
	 */
	public static String[] dateStringArrayChange(String[] originalStrArray){
		for(int i = 0; i < originalStrArray.length; i ++){
			originalStrArray[i] = dateStringChange(originalStrArray[i]);
		}
		return originalStrArray;
	}
	
	public static String databaseStr2TextViewStr(String str){
		if(str == null){
			return "无信息";
		}
		
		if(str.equals("null")){
			str = "无信息";
		}
		else if(StringUtils.isDateString(str)){
			str = StringUtils.dateString2zhDateString(str);
		}
		else if(StringUtils.isDateRangeString(str)){
			str = StringUtils.dateRangeString2zhDateRangeString(str);
		}
		return str;
	}
}
