package com.chenjun.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.chenjun.R;
import com.chenjun.fund.model.FundReport;
import com.chenjun.fund.model.FundReportList;
import com.chenjun.listviewadapter.FundBaseInfoListViewAdapter;
import com.chenjun.network.InitFundReport;
import com.chenjun.view.SyncHorizontalScrollView;

public class FundReportActivity extends Activity implements GoToFundInfoActivityGroup, OnScrollListener{
	private static final int INIT_ITEM_COUNT = 30;
	private static final int ONCE_SCROLL_ADD_COUNT = 10;
	
	private ListView listView;
	private SyncHorizontalScrollView headView;
	private FundReportList fundReportList;
	
	private FundBaseInfoListViewAdapter listAdapter;
	
	private int itemCount = INIT_ITEM_COUNT;
	
	private int lastVisibleItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fundreportactivity);
		super.onCreate(savedInstanceState);
		
		listView = (ListView)findViewById(R.id.fundreport_activity_listView);
		headView = (SyncHorizontalScrollView)findViewById(R.id.fundbaseinfo_list_head_scrollView);
		
		headView.setSyncView(listView);
		
		fundReportList = InitFundReport.fundReportList;
		setListData();
		listView.setOnScrollListener(this);
	}
	
	@Override
	public void handle(Intent intent) {
		startActivity(intent);
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		this.lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
		
		if(lastVisibleItem == (itemCount - 2)){
			listAdapter.addDate(getListMapData(itemCount, itemCount + ONCE_SCROLL_ADD_COUNT));
			itemCount += ONCE_SCROLL_ADD_COUNT;
			listAdapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		//达到列表的底部
		if(scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibleItem == (itemCount - 1)){
			listAdapter.addDate(getListMapData(itemCount, itemCount + ONCE_SCROLL_ADD_COUNT));
			itemCount += ONCE_SCROLL_ADD_COUNT;
			listAdapter.notifyDataSetChanged();
		}
	}
	
	/**
	 * 设置列表内容
	 */
	private void setListData() {
		if (fundReportList == null) {
			return;
		}

		listAdapter = new FundBaseInfoListViewAdapter(this, getListMapData(0 , INIT_ITEM_COUNT), this, headView);
		
		listView.setAdapter(listAdapter);
	}
	
	
	protected List<FundReport> getListMapData(int start, int end){
		
		if(start < 0 || end < 0 || start > fundReportList.getFundReportList().size()){
			return null;
		}
		
		if(end > fundReportList.getFundReportList().size()){
			end = fundReportList.getFundReportList().size();
		}
		
		List<FundReport> mData = new ArrayList<FundReport>();

		for (int i = start; i < end; i++) {
			mData.add(fundReportList.getFundReportList().get(i));
		}
		
		return mData;
	}
}
