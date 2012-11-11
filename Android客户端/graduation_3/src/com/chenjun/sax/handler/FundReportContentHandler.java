package com.chenjun.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.chenjun.fund.model.FundReportList;

public class FundReportContentHandler extends DefaultHandler{
	private final String FUNDREPORTLIST_TAG_NAME = "FundReportList";
	private final String DATE_TAG_NAME = "Date";
	private final String DATE_OLD_TAG_NAME = "DateOld";
	private final String FUNDREPORT_TAG_NAME = "FundReport";
	
	private FundReportList fundReportList;
	
	private String tagName = null;
	
	public FundReportContentHandler(FundReportList fundReportList){
		this.fundReportList = fundReportList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagName = localName;
		//System.out.println("start element -->" + localName);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		StringBuffer sb = new StringBuffer();
		sb.append(ch, start, length);
		if(FUNDREPORTLIST_TAG_NAME.equals(tagName)){
			
		}
		else if(DATE_TAG_NAME.equals(tagName)){
			fundReportList.setDate(sb.toString());
			System.out.println(sb.toString());
		}
		else if(DATE_OLD_TAG_NAME.equals(tagName)){
			fundReportList.setDateOld(sb.toString());
			System.out.println(sb.toString());
		}
		else if(FUNDREPORT_TAG_NAME.equals(tagName)){
			fundReportList.addFundReport(sb.toString());
			System.out.println(sb.toString());
		}
	}
	
}
