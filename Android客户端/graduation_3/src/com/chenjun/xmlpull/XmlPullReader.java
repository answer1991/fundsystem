package com.chenjun.xmlpull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.chenjun.fund.model.FundReportList;

public class XmlPullReader {
	
	public static FundReportList parseFundReportListXml(File xmlFile) {
		XmlPullParser parser = Xml.newPullParser();
		try {
			InputStream is = new FileInputStream(xmlFile);
			parser.setInput(is, "utf-8");
			int eventType = parser.getEventType();
			FundReportList fundReportList = null;
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					fundReportList = new FundReportList();
					break;
					
				case XmlPullParser.START_TAG:// 开始元素事件
					String name = parser.getName();
					if (name.equalsIgnoreCase("Date")) {
						fundReportList.setDate(parser.nextText());
					}
					else if (name.equalsIgnoreCase("DateOld")) {
						fundReportList.setDateOld(parser.nextText());
					}
					else if (name.equalsIgnoreCase("FundReport")) {
						fundReportList.addFundReport(parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// 结束元素事件
					
					break;
				}
				eventType = parser.next();
			}
			
			is.close();
			
			fundReportList.initFundReportList();
			
			return fundReportList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
