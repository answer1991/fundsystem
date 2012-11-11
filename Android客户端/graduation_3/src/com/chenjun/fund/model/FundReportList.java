package com.chenjun.fund.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FundReportList {
	private String date;
	private String dateOld;
	private List<String> fundReportStrList;
	private List<FundReport> fundReportList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateOld() {
		return dateOld;
	}

	public void setDateOld(String dateOld) {
		this.dateOld = dateOld;
	}

	public void addFundReport(String fundReport) {
		if(null == fundReportStrList){
			fundReportStrList =  new ArrayList<String>();
		}
		fundReportStrList.add(fundReport);
	}

	public List<String> getFundReportStrList() {
		return fundReportStrList;
	}

	public void setFundReportStrList(List<String> fundReportStrList) {
		this.fundReportStrList = fundReportStrList;
	}

	public List<FundReport> getFundReportList() {
		return fundReportList;
	}

	public void setFundReportList(List<FundReport> fundReportList) {
		this.fundReportList = fundReportList;
	}

	public void initFundReportList() {
		if(fundReportStrList == null){
			return;
		}
		
		if(null == fundReportList){
			fundReportList = new ArrayList<FundReport>();
		}
		
		for (int i = 0; i < fundReportStrList.size(); i++) {
			FundReport fundReport = new FundReport();
			
			String fundReportStr = fundReportStrList.get(i);
			
			String[] infoArray = fundReportStr.split(",");
			
			if(infoArray.length != 45){
				continue;
			}

			String dm = infoArray[0]; // 基金代码,
			fundReport.setDm(dm);
			
			String jjjc = infoArray[1]; // 基金简称,
			fundReport.setJjjc(jjjc);
			
			String tzlx = infoArray[2]; // 投资类型,
			fundReport.setTzlx(tzlx);
			
			String dycjjrq = infoArray[3]; // 第一次的净值日期,
			fundReport.setDycjjrq(dycjjrq);
			
			String zxrq = infoArray[4]; // 最新日期,
			fundReport.setZxrq(zxrq);
			
			String zxjz = infoArray[5]; // 最新净值,
			fundReport.setZxjz(zxjz);
			
			String ljjz = infoArray[6]; // 累计净值,
			fundReport.setLjjz(ljjz);
			
			String fqjz = infoArray[7]; // 复权净值,
			fundReport.setFqjz(fqjz);
			
			String sqrq = infoArray[8]; // 上期日期,
			fundReport.setSqrq(sqrq);
			
			String sqjz = infoArray[9]; // 上期净值,
			fundReport.setSqjz(sqjz);
			
			String sqljjz = infoArray[10]; // 上期累计净值,
			fundReport.setSqljjz(sqljjz);
			
			String sqfqjz = infoArray[11]; // 上期复权净值,
			fundReport.setSqfqjz(sqfqjz);
			
			String rzd = infoArray[12]; // 日涨跌,
			fundReport.setRzd(rzd);
			
			String rzf = infoArray[13]; // 日涨幅,
			fundReport.setRzf(rzf);
			
			String zzf = infoArray[14]; // 周涨幅,
			fundReport.setZzf(zzf);
			
			String yzf = infoArray[15]; // 月涨幅,
			fundReport.setYzf(yzf);
			
			String jzf = infoArray[16]; // 季涨幅,
			fundReport.setJzf(jzf);
			
			String bnzf = infoArray[17]; // 半年涨幅,
			fundReport.setBnzf(bnzf);
			
			String ynzf = infoArray[18]; // 一年涨幅,
			fundReport.setYnzf(ynzf);
			
			String jnylzf = infoArray[19]; // 今年以来涨幅,
			fundReport.setJnylzf(jnylzf);
			
			String lnzf = infoArray[20]; // 两年涨幅,
			fundReport.setLnzf(lnzf);
			
			String snzf = infoArray[21]; // 三年涨幅,
			fundReport.setSnzf(snzf);
			
			String wnzf = infoArray[22]; // 五年涨幅,
			fundReport.setWnzf(wnzf);
			
			String clylzf = infoArray[23]; // 成立以来涨幅,
			fundReport.setClylzf(clylzf);
			
			String rzftlxpm = infoArray[24]; // 日涨幅同类型排名,
			fundReport.setRzftlxpm(rzftlxpm);
			
			String syrrzftlxpm = infoArray[25]; // 上一日日涨幅同类型排名(注, 仅为货币基金设置,相当于上一日收益排名),
			fundReport.setSyrrzftlxpm(syrrzftlxpm);
			
			String zzftlxpm = infoArray[26]; // 周涨幅同类型排名,
			fundReport.setZzftlxpm(zzftlxpm);
			
			String yzftlxpm = infoArray[27]; // 月涨幅同类型排名,
			fundReport.setYzftlxpm(yzftlxpm);
			
			String jdzftlxpm = infoArray[28]; // 季度涨幅同类型排名,
			fundReport.setJdzftlxpm(jdzftlxpm);
			
			String bnzftlxpm = infoArray[29]; // 半年涨幅同类型排名,
			fundReport.setBnzftlxpm(bnzftlxpm);
			
			String nzftlxpm = infoArray[30]; // 年涨幅同类型排名,
			fundReport.setNzftlxpm(nzftlxpm);
			
			String jnzftlxpm = infoArray[31]; // 今年涨幅同类型排名,
			fundReport.setJnzftlxpm(jnzftlxpm);
			
			String lnzftlxpm = infoArray[32]; // 两年涨幅同类型排名,
			fundReport.setLnzftlxpm(lnzftlxpm);
			
			String snzftlxpm = infoArray[33]; // 三年涨幅同类型排名,
			fundReport.setSnzftlxpm(snzftlxpm);
			
			String wnzftlxpm = infoArray[34]; // 五年涨幅同类型排名,
			fundReport.setWnzftlxpm(wnzftlxpm);
			
			String rzfzpm = infoArray[35]; // 日涨幅总排名,
			fundReport.setRzfzpm(rzfzpm);
			
			String zzfzpm = infoArray[36]; // 周涨幅总排名,
			fundReport.setZzfzpm(zzfzpm);
			
			String yzfzpm = infoArray[37]; // 月涨幅总排名,
			fundReport.setYzfzpm(yzfzpm);
			
			String jdzfzpm = infoArray[38]; // 季度涨幅总排名,
			fundReport.setJdzfzpm(jdzfzpm);
			
			String bnzfzpm = infoArray[39]; // 半年涨幅总排名,
			fundReport.setBnzfzpm(bnzfzpm);
			
			String nzfzpm = infoArray[40]; // 年涨幅总排名,
			fundReport.setNzfzpm(nzfzpm);
			
			String jnzfzpm = infoArray[41]; // 今年涨幅总排名,
			fundReport.setJnzfzpm(jnzfzpm);
			
			String lnzfzpm = infoArray[42]; // 两年涨幅总排名,
			fundReport.setLnzfzpm(lnzfzpm);
			
			String snzfzpm = infoArray[43]; // 三年涨幅总排名,
			fundReport.setSnzfzpm(snzfzpm);
			
			String wnzfzpm = infoArray[44]; // 五年涨幅总排名
			fundReport.setWnzfzpm(wnzfzpm);
			
			fundReportList.add(fundReport);
		}
	}
	
	public List<FundReport> search(String keyWord){
		List<FundReport> list = new ArrayList<FundReport>();
		
		FundReport temp = null;
		for(int i = 0; i < fundReportList.size(); i++){
			temp = fundReportList.get(i);
			
			if(temp.getDm().contains(keyWord) || temp.getJjjc().contains(keyWord)){
				list.add(temp);
			}
		}
		
		return list;
	}
	
	public List<FundReport> getSelfCheckFund(List<String> dms){
		List<FundReport> list = new ArrayList<FundReport>();
		List<String> tempDms = new ArrayList<String>(dms);
		
		FundReport temp = null;
		for(int i = 0; i < fundReportList.size(); i++){
			temp = fundReportList.get(i);
			
			for(int j = 0; j < tempDms.size(); j ++){
				if(temp.getDm().equals(tempDms.get(j))){
					list.add(temp);
					tempDms.remove(j);
				}
			}
		}
		
		return list;
	}
}
