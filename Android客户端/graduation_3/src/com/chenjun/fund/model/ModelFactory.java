package com.chenjun.fund.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ModelFactory {
	
	private static final Gson gson = new Gson();
	
	/**
	 * 将Json File文件转化为字符串形式
	 * @param jsonFile Json文件
	 * @return Json字符串
	 */
	private static String fileToString(File jsonFile){
		String jsonString = new String();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(jsonFile);
			byte[] buffer =  new byte[1024];
			int i;
			while((i = fis.read(buffer)) != -1){
				jsonString += new String(buffer, 0, i, "gb2312");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fis != null){
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonString;
	}
	
	/**
	 * 将Json文件路径转化为Json字符串
	 * @param jsonFilePath Json文件路径
	 * @return Json字符串
	 */
	public static String filePathToString(String jsonFilePath){
		File jsonFile = new File(jsonFilePath);
		return fileToString(jsonFile);
	}
	
	public static LoginStatus getLoginStatus(String jsonString){
		return gson.fromJson(jsonString, LoginStatus.class);
	}
	
	public static AddSelfCheckStatus getAddSelfCheckStatus(String jsonString){
		return gson.fromJson(jsonString, AddSelfCheckStatus.class);
	}
	
	/**
	 * 从Json字符串中获得单个基金净值对象
	 * @param jsonString Json字符串
	 * @return Jjjz对象
	 */
	public static Jjjz getJjjz(String jsonString){
		Gson gson = new Gson();
		return gson.fromJson(jsonString, Jjjz.class);
	}
	
	/**
	 * 从Json文件获得单个基金净值对象
	 * @param jsonFile Json文件
	 * @return Jjjz对象
	 */
	public static Jjjz getJjjz(File jsonFile){
		String jsonString = fileToString(jsonFile);
		return getJjjz(jsonString);
	}
	
	/**
	 * 将Json字符串转化为多个（一段时间内）基金净值对象
	 * @param jsonString Json字符串
	 * @return Jjjzs对象
	 */
	public static Jjjzs getJjjzs(String jsonString){
		List<Jjjz> jjjzs = getJjjzList(jsonString);
		return new Jjjzs(jjjzs);
	}
	
	/**
	 * 将Json字符串转化为多个（一段时间内）基金净值对象，以List<Jjjz>的形式
	 * @param jsonString
	 * @return
	 */
	public static List<Jjjz> getJjjzList(String jsonString){
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<Jjjz>>() {}.getType();
		LinkedList<Jjjz> jjjzs = gson.fromJson(jsonString, type);
		return jjjzs;
	}
	
	/**
	 * 将Json文件转化为多个（一段时间内）基金净值对象
	 * @param jsonFile Json文件
	 * @return Jjjzs对象
	 */
	public static Jjjzs getJjjzs(File jsonFile){
		String jsonString = fileToString(jsonFile);
		//System.out.println(json);
		return getJjjzs(jsonString);
	}
	
	/**
	 * 将Json字符串转化为基金概况
	 * @param jsonString Json字符串
	 * @return Jjgk对象
	 */
	public static Jjgk getJjgk(String jsonString){
		Gson gson = new Gson();
		return gson.fromJson(jsonString, Jjgk.class);
	}
	
	/**
	 * 将Json字符串转化为多个基金分红对象
	 * @param jsonString Json字符串
	 * @return List<Jjfh> Jjfh数组
	 */
	public static List<Jjfh> getJjfhs(String jsonString){
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<Jjfh>>() {}.getType();
		LinkedList<Jjfh> jjfhs = gson.fromJson(jsonString, type);
		return jjfhs;
	}
	
	/**
	 * 将Json字符串转化为基金公司对象
	 * @param jsonString Json字符串
	 * @return Jjgs Jjgs对象
	 */
	public static Jjgs getJjgs(String jsonString){
		Gson gson = new Gson();
		return gson.fromJson(jsonString, Jjgs.class);
	}
	
	/**
	 * 将Json字符串转化为多个基金拆分对象
	 * @param jsonString Json字符串
	 * @return List<Jjfh> Jjfh数组
	 */
	public static List<Jjcf> getJjcfs(String jsonString){
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<Jjcf>>() {}.getType();
		LinkedList<Jjcf> jjcfs = gson.fromJson(jsonString, type);
		return jjcfs;
	}
	
	/**
	 * 将Json字符串转化为多个基金基本信息对象
	 * @param jsonString Json字符串
	 * @return List<Jjfh> Jjfh数组
	 */
	public static List<JjBaseInfo> getJjBaseInfos(String jsonString){
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<JjBaseInfo>>() {}.getType();
		LinkedList<JjBaseInfo> jjBaseInfos = gson.fromJson(jsonString, type);
		return jjBaseInfos;
	}
}
