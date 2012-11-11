package com.chenjun.activities;

import java.io.File;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chenjun.R;
import com.chenjun.fund.model.FundReportList;
import com.chenjun.network.HttpDownloader;
import com.chenjun.network.InitFundReport;
import com.chenjun.utils.FileUtils;
import com.chenjun.utils.ThreadPoolUtils;
import com.chenjun.xmlpull.XmlPullReader;

public class MainWindowActivityGroup extends ActivityGroup implements
CompoundButton.OnCheckedChangeListener{
	private static final String FUNDREPORT_ACTIVITY = "fundReportActivity";
	private static final String SEARCH_ACTIVITY = "searchActivity";
	private static final String SELF_CHECK_FUND_ACTVITY = "selfCheckFundActivity";

	private static final int FUNDREPORT_RECEIVE = 1;
	private static final int START_DOWNLOAD_MESSAGE = 2;
	private static final int DOWNLOAD_ERROR_MESSAGE = 3;

	private LinearLayout container;
	
	private ProgressDialog waitDialog;
	
	private RadioButton radio_button0;
	private RadioButton radio_button1;
	private RadioButton radio_button2;
	private RadioButton radio_button3;
	private RadioButton radio_button4;

	private FundReportList fundReportList;

	//private Thread downloadThread; // 下载基金走势的线程
	private Handler refleshHandler = new RefleshHandler(); // 刷新Handler，用于用户按钮点击或数据到达时处理对应事件
	private LocalActivityManager activityManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.mainwindowactivitygroup);
		super.onCreate(savedInstanceState);

		container = (LinearLayout) findViewById(R.id.mainWindowContainerBody);

		TextView test = new TextView(this);
		test.setText("数据请求中");
		container.addView(test);

		ThreadPoolUtils.execute(new DownloadRunnable());
		
		activityManager = getLocalActivityManager();
		// initContainerView();

		// fundReportList =
		// XmlPullReader.parseFundReportListXml(InitFundReport.reportFile);
		// System.out.println(fundReportList.getFundReportList().get(0).getDm());
	}
	
	private void initRadios() {
		this.radio_button0 = ((RadioButton) findViewById(R.id.radio_button0));
		this.radio_button1 = ((RadioButton) findViewById(R.id.radio_button1));
		this.radio_button2 = ((RadioButton) findViewById(R.id.radio_button2));
		this.radio_button3 = ((RadioButton) findViewById(R.id.radio_button3));
		this.radio_button4 = ((RadioButton) findViewById(R.id.radio_button4));
		this.radio_button0.setOnCheckedChangeListener(this);
		this.radio_button1.setOnCheckedChangeListener(this);
		this.radio_button2.setOnCheckedChangeListener(this);
		this.radio_button3.setOnCheckedChangeListener(this);
		this.radio_button4.setOnCheckedChangeListener(this);
	}

	
	private Intent initIntent(Class<?> cls){
		return new Intent(this,	cls).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}
	
	private void addContent(String activityName, Class<?> activityClassTye){
		container.removeAllViews();
		Activity jjcfActivity = activityManager.getActivity(activityName);
		if (jjcfActivity == null) {
			activityManager.startActivity(activityName, initIntent(activityClassTye));
		}

		container.addView(
				activityManager.getActivity(activityName)
						.getWindow().getDecorView(),
				new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT));
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			
			case R.id.radio_button0:
				addContent(FUNDREPORT_ACTIVITY, FundReportActivity.class);
				break;
				
			case R.id.radio_button1:
				addContent(SEARCH_ACTIVITY, SearchActivity.class);
				break;
				
			case R.id.radio_button2:
				addContent(SELF_CHECK_FUND_ACTVITY, SelfCheckFundActivity.class);
				break;
				
			case R.id.radio_button3:
				break;
				
			case R.id.radio_button4:
				break;
				
			default:
				break;
			}
		}
		
	}

	/**
	 * 下载线程的具体函数
	 * 
	 * @author zet
	 * 
	 */
	private class DownloadRunnable implements Runnable {

		public void run() {

			File reportFile = FileUtils.getReportFile();
			
			refleshHandler.sendEmptyMessage(START_DOWNLOAD_MESSAGE);
			try {
				if (HttpDownloader.downloadReport()) {
					fundReportList = XmlPullReader
							.parseFundReportListXml(reportFile);
					
					InitFundReport.fundReportList = fundReportList;

					// 发送对应事件给Handler
					refleshHandler.sendEmptyMessage(FUNDREPORT_RECEIVE);
				}
			} catch (Exception e) {
				refleshHandler.sendEmptyMessage(DOWNLOAD_ERROR_MESSAGE);
				System.out.println("请求数据失败！");
			}

		}
	}

	/**
	 * 重写写Hanlder对应调度的函数
	 * 
	 * @author zet
	 * 
	 */
	private class RefleshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case FUNDREPORT_RECEIVE:
				fundReportReceive();
				break;
				
			case START_DOWNLOAD_MESSAGE:
				startDownload();
				break;
				
				
				
			default:
				break;
			}

		}
	}
	
	private void fundReportReceive(){
		MainWindowActivityGroup.this.initRadios();
		MainWindowActivityGroup.this.radio_button0.setChecked(true);
		hideWaitDialog();
		Toast.makeText(this, getString(R.string.Globe_Report_Download_Success), Toast.LENGTH_SHORT);
	}
	
	private void startDownload(){
		showWaitDialog();
	}
	
	private void showWaitDialog(){
		if(waitDialog == null){
			waitDialog = new ProgressDialog(this);
			waitDialog.setMessage(getString(R.string.Globe_Report_Download_Wait_ProgressDialog));
		}
		
		waitDialog.show();
	}
	
	private void hideWaitDialog(){
		if(waitDialog != null){
			waitDialog.hide();
		}
	}
	
	
	public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 4)
				&& (paramKeyEvent.getAction() == 0)
				&& (paramKeyEvent.getRepeatCount() == 0))
			new AlertDialog.Builder(this)
					.setMessage("确定退出?")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
								}
							})
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
									MainWindowActivityGroup.this.finish();
									System.gc();
									System.exit(0);
								}
							}).show();
		return true;
	}
}
