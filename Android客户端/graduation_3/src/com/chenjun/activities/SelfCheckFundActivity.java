package com.chenjun.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.TextView;

import com.chenjun.R;
import com.chenjun.fund.model.FundReport;
import com.chenjun.network.InitFundReport;


public class SelfCheckFundActivity extends FundReportActivity {
	private List<FundReport> selfCheckFundReportList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initSelfCheckFund();
		super.onCreate(savedInstanceState);
		
		((TextView)findViewById(R.id.FundReportActivity_Title_TextView)).setText(getString(R.string.SelfCheckFundActivity_FundReport_ActivityLabel));
	}
	
	private void initSelfCheckFund(){
		selfCheckFundReportList = InitFundReport.fundReportList.getSelfCheckFund(InitFundReport.loginStatus.getSelfCheckJjDms());
		System.out.println(selfCheckFundReportList.size());
	}
	
	@Override
	protected List<FundReport> getListMapData(int start, int end){
		
		if(start < 0 || end < 0 || start > selfCheckFundReportList.size()){
			return null;
		}
		
		if(end > selfCheckFundReportList.size()){
			end = selfCheckFundReportList.size();
		}
		
		List<FundReport> mData = new ArrayList<FundReport>();

		for (int i = start; i < end; i++) {
			mData.add(selfCheckFundReportList.get(i));
		}
		
		return mData;
	}
}