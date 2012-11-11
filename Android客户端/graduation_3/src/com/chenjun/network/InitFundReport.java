package com.chenjun.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.chenjun.fund.model.FundReportList;
import com.chenjun.fund.model.LoginStatus;
import com.chenjun.sax.handler.FundReportContentHandler;


/**
 * 这个类提供一个静态变量和方法，能够每次运行程序时获取最新的基金排名报告
 * @author zet
 *
 */
public class InitFundReport {
	public static File reportFile;
	public static FundReportList fundReportList;
	public static LoginStatus loginStatus;
	
//	static{
//		try {
//			reportFile = HttpDownloader.downloadAsFile(NetWorkConfig.getReportUrl(), "androidFund/cache", "Report.xml");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 不用这个方法了。
	 * @param fundReportList
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void parseXML(FundReportList fundReportList) throws SAXException, ParserConfigurationException, FileNotFoundException, IOException{
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		XMLReader xmlReader = saxFactory.newSAXParser().getXMLReader();
		xmlReader.setContentHandler(new FundReportContentHandler(fundReportList));
		xmlReader.parse(new InputSource(new FileReader(reportFile)));
		
		System.out.println(fundReportList.getDate());
	}
}
