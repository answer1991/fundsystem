package com.chenjun.utils;

import java.util.regex.Matcher;

public class StringUtils {
	public static String replace(String str){
		if(str == null){
			return str;
		}
		return str.replaceAll(Matcher.quoteReplacement("\""), Matcher.quoteReplacement("\\\""));
	}
}
