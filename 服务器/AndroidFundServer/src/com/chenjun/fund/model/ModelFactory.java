package com.chenjun.fund.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ModelFactory {
	
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
	private static String filePathToString(String jsonFilePath){
		File jsonFile = new File(jsonFilePath);
		return fileToString(jsonFile);
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
	 * 将Json字符串转化为多个基金净值对象
	 * @param jsonString Json字符串
	 * @return LinkedList<Jjjz>对象
	 */
	public static LinkedList<Jjjz> getJjjzs(String jsonString){
		Gson gson = new Gson();
		Type type = new TypeToken<LinkedList<Jjjz>>() {}.getType();
		LinkedList<Jjjz> jjjzs = gson.fromJson(jsonString, type);
		return jjjzs;
	}
	
	/**
	 * 将Json文件转化为多个基金净值对象
	 * @param jsonFile Json文件
	 * @return LinkedList<Jjjz>对象
	 */
	public static LinkedList<Jjjz> getJjjzs(File jsonFile){
		String jsonString = fileToString(jsonFile);
		//System.out.println(json);
		return getJjjzs(jsonString);
	}
}
