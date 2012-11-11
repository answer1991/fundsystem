package com.chenjun.network;

public class NetWorkConfig {
//	public static final String ipAddr = "http://10.0.2.2:8081";
//	public static final String ipAddr = "http://androidfund.3322.org:8081";
	public static final String ipAddr = "http://60.176.46.85:8081";
	public static final String rootPath = "/AndroidFundServer";
	
	public static final String reportPath = "/fundreport.json";
	public static final String loginPath = "/login.json";
	public static final String addToSelfCheckFundPath = "/addSelfCheckFund.json";
	public static final String jjjzsPath = "/jjjz.json";
	public static final String jjgkPath = "/jjgk.json";
	public static final String jjfhPath = "/jjfh.json";
	public static final String jjcfPath = "/jjcf.json";
	public static final String jjgsPath = "/jjgs.json";
	
	public static final String searchJson = "/search.json";
	
	public static final String startParameter = "?";
	public static final String andParameter = "&";
	public static final String dmParameter = "dm=";
	public static final String dateParameter = "date=";
	public static final String dayCountParameter = "dayCount=";
	public static final String lastDateParameter = "lastDate=";
	public static final String usernameParameter = "username=";
	public static final String passwordParameter = "password=";
	public static final String accountIdParameter = "accountId=";
	public static final String infoParameter = "info=";
	
	public static String getSearchUrl(String keyWord){
		return ipAddr + rootPath + searchJson + startParameter + infoParameter + keyWord;
	}
	
//	public static String getReportUrl(){
//		return ipAddr + rootPath + reportPath;
//	}
	
//	public static String getJjjzUrl(String dm, String dayCount){
//		return ipAddr + rootPath + jjjzsPath + startParameter + dmParameter + dm ;
//	}
	
	public static String getLoginUrl(String username, String password){
		return ipAddr + rootPath + loginPath + startParameter + usernameParameter + username + andParameter + passwordParameter + password ;
	}
	
	public static String getAddSelfCheckFundUrl(String accountId, String dm){
		return ipAddr + rootPath + addToSelfCheckFundPath + startParameter + accountIdParameter + accountId +  andParameter + dmParameter + dm;
	}
	
	public static String getJjjzUrl(String dm){
		return ipAddr + rootPath + jjjzsPath + startParameter + dmParameter + dm; 
	}
	
	public static String getJjjzUrl(String dm, String lastDate){
		return ipAddr + rootPath + jjjzsPath + startParameter + dmParameter + dm + andParameter + lastDateParameter + lastDate ;
	}
	
	public static String getJjgkUrl(String dm){
		return ipAddr + rootPath + jjgkPath + startParameter + dmParameter + dm;
	}
	
	public static String getJjfhUrl(String dm){
		return ipAddr + rootPath + jjfhPath + startParameter + dmParameter + dm;
	}
	
	public static String getJjcfUrl(String dm){
		return ipAddr + rootPath + jjcfPath + startParameter + dmParameter + dm;
	}
	
	public static String getJjgsUrl(String dm){
		return ipAddr + rootPath + jjgsPath + startParameter + dmParameter + dm;
	}
	
	public static String getReportUrl(){
		return ipAddr + rootPath + reportPath;
	}
	
	public static String getReportUrl(String date){
		return ipAddr + rootPath + reportPath + startParameter + dateParameter + date;
	}
}
