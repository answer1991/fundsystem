package com.chenjun.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chenjun.R;
import com.chenjun.fund.model.AddSelfCheckStatus;
import com.chenjun.fund.model.Jjjz;
import com.chenjun.fund.model.Jjjzs;
import com.chenjun.fund.model.ModelFactory;
import com.chenjun.network.HttpDownloader;
import com.chenjun.network.InitFundReport;
import com.chenjun.network.NetWorkConfig;
import com.chenjun.provider.helper.ProviderStaticHelper;
import com.chenjun.utils.StringUtils;
import com.chenjun.utils.ThreadPoolUtils;
import com.chenjun.view.JjjzsChart;
import com.chenjun.view.JjjzsChart.ChartType;
import com.chenjun.view.RangeInfoViewGroup;

/**
 * 基金净值走势的Activity，包括基本信息，走势图和统计区
 * @author zet
 *
 */
public class JjjzsActivity extends Activity{
	
	private static final int JJJZ_CHART_REFLESH = 1;
	private static final int JJJZ_BASEINFO_REFLESH = 2;
	private static final int DOWNLOAD_SUCCESS = 3;
	private static final int DOWNLOAD_ERROR = 4;
	private static final int ADD_SELF_CHECK_FUND_SUCCESS = 5;
	private static final int ADD_SELF_CHECK_FUND_FAIL = 6;
	private static final int NET_WORK_ERROR = 0;
	
	private JjjzsChart jjjzsChart;								//走势图
	private Jjjzs jjjzs;										//基金净值历史走势信息
	//private Thread downloadThread;								//下载基金走势的线程
	private Handler refleshHandler = new RefleshHandler();		//刷新Handler，用于用户按钮点击或数据到达时处理对应事件
	
	private String dm;
	private String jjjc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.jjjzsactivity);
		super.onCreate(savedInstanceState);
		jjjzsChart = (JjjzsChart)findViewById(R.id.chart);
		
		Intent intent = getIntent();
		jjjc = intent.getStringExtra("jjjc");
		dm = intent.getStringExtra("dm");
		
		refleshBaseInfo();
		
		//开启下载线程
		ThreadPoolUtils.execute(new DownloadRunnable(dm));
		
		initBtn();
		bindRangeInfoViewGroup();
	}
	
	@Override
	protected void onDestroy() {
//		downloadThread.stop()
		super.onDestroy();
	}
	
	/**
	 * 下载线程的具体函数
	 * 线程的具体操作步骤：
	 *    1.根据基金的代码，从本地缓存数据库里取得对应这个基金的所有旧的基金净值信息
	 *    2.取得旧的基金净值信息的最后一天日期
	 *    3.根据基金代码和最后一天日期，向服务器请求新的基金净值信息
	 *    4.合并 旧的净值信息和新的净值信息，并通知UI线程，净值信息已在内存中准备完毕，UI线程可以开始刷新图表的信息
	 *    5.将新的净值信息写入本地缓存数据库
	 * @author zet
	 *
	 */
	private class DownloadRunnable implements Runnable {
		
		private String dm;
		
		public DownloadRunnable(String dm){
			this.dm = dm;
		}
		
		public void run() {
			System.out.println("开始请求数据");
			try {
				//根据代码，查询本地缓存数据库的基金净值信息
				List<Jjjz> jjjzList = ProviderStaticHelper.getJjjsListFromDB(JjjzsActivity.this, dm);
				
				//根据本地缓存情况，获得最后基金净值的日期，再将信息组织成url，向服务器请求
				String url = null;
				
				if(jjjzList.size() > 0){
					String lastDate = jjjzList.get(jjjzList.size() - 1).getRq();
					url = NetWorkConfig.getJjjzUrl(dm, lastDate);
				}
				else {
					url = NetWorkConfig.getJjjzUrl(dm);
				}
				
				String json = HttpDownloader.downloadCompressedByte(url);
				
				List<Jjjz> downloadJjjzList = ModelFactory.getJjjzList(json);
				
				//合并缓存和新净值数据
				jjjzList.addAll(downloadJjjzList);
				
				jjjzs = new Jjjzs(jjjzList);
				
				jjjzsChart.setJjjzs(jjjzs);
				
				System.out.println("数据转换成功！");
				
				
				//处理完成，发送对应事件给Handler，通知UI线程可以刷新UI
				refleshHandler.sendEmptyMessage(JJJZ_CHART_REFLESH);
				refleshHandler.sendEmptyMessage(JJJZ_BASEINFO_REFLESH);
				refleshHandler.sendEmptyMessage(DOWNLOAD_SUCCESS);
				
				//将新的净值信息写入缓存数据库
				ProviderStaticHelper.writeJjjzListToDB(JjjzsActivity.this, downloadJjjzList);
				
				
			} catch (Exception e) {
				refleshHandler.sendEmptyMessage(DOWNLOAD_ERROR);
				System.out.println("请求数据失败！");
			}
			
		}
	}
	
	/**
	 * UI刷新的Hanlder
	 * @author zet
	 *
	 */
	private class RefleshHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			//图表刷新
			if(msg.what == JJJZ_CHART_REFLESH){
				JjjzsActivity.this.jjjzsChart.postInvalidate();
				//JjjzsActivity.this.writeToDB();
			}
			//基本信息刷新
			else if(msg.what == JJJZ_BASEINFO_REFLESH){
				refleshJzAndIncrease();
			}
			
			//下载成功
			else if(msg.what == DOWNLOAD_SUCCESS){
				Toast.makeText(JjjzsActivity.this, "净值请求成功！", Toast.LENGTH_SHORT).show();
			}
			
			//下载失败
			else if(msg.what == DOWNLOAD_ERROR){
				Toast.makeText(JjjzsActivity.this, "净值请求失败！", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	 *  让JjjzsChart和统计区关联，实现动态刷新
	 */
	private void bindRangeInfoViewGroup(){
		RangeInfoViewGroup rangeInfoViewGroup = new RangeInfoViewGroup();
		
		rangeInfoViewGroup.setStartDateTextView((TextView)this.findViewById(R.id.startTimeValue));
		rangeInfoViewGroup.setEndDateTextView((TextView)this.findViewById(R.id.endTimeValue));
		rangeInfoViewGroup.setStartJjTextView((TextView)this.findViewById(R.id.startJzValue));
		rangeInfoViewGroup.setEndJjTextView((TextView)this.findViewById(R.id.endJzValue));
		rangeInfoViewGroup.setRangeIncreaseTextView((TextView)this.findViewById(R.id.increaseNumValue));
		rangeInfoViewGroup.setRangeIncreasePrecentTextView((TextView)this.findViewById(R.id.increasePercentValue));
		
		this.jjjzsChart.setRangeInfoViewGroup(rangeInfoViewGroup);
	}
	
	/**
	 * 基本信息刷新的函数
	 * 净值走势信息到达后，只要调用这个函数，就能在Activity基本信息栏刷新基金的信息
	 */
	private void refleshJzAndIncrease(){
		Jjjz lastJjjz = jjjzs.getJjjz(jjjzs.getCount() -1);
		Jjjz preJjjz = jjjzs.getJjjz(jjjzs.getCount() - 2);
		
		if(lastJjjz == null || preJjjz == null){
			return;
		}
		
		String jz = StringUtils.stringLenthFormat(lastJjjz.getJz(), 6, '0');
		String date = StringUtils.dateStringChange(lastJjjz.getRq());
		
		TextView jzTextView = (TextView)this.findViewById(R.id.JjjzsActivity_jjjzTextView);
		jzTextView.setText(jz);
		
		TextView dateTextView = (TextView)this.findViewById(R.id.JjjzsActivity_jjjzDateTextView);
		dateTextView.setText(date);

		double jzIncrease = Double.parseDouble(lastJjjz.getFqjz()) - Double.parseDouble(preJjjz.getFqjz());
		
		TextView jzIncreaseTextView = (TextView)this.findViewById(R.id.JjjzsActivity_jzIncreaseTextView);
		if(jzIncrease < 0){		
			String jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 7, '0');
			jzIncreaseTextView.setTextColor(Color.GREEN);
			jzIncreaseTextView.setText(jzIncreaseStr);
		}
		else{
			String jzIncreaseStr = StringUtils.stringLenthFormat(String.valueOf(jzIncrease), 6, '0');
			jzIncreaseTextView.setTextColor(Color.RED);
			jzIncreaseTextView.setText(jzIncreaseStr);
		}
	}
	
	/**
	 * 初始化显示基金天数的按钮
	 */
	private void initBtn(){
		 ImageButton tenDayBtn = (ImageButton) findViewById(R.id.tenDayBtn);
		 tenDayBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	btnClickFunction(ChartType.TEN_DAY);
	            }
	        });
		 
		 ImageButton monthBtn = (ImageButton) findViewById(R.id.monthBtn);
		 monthBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	btnClickFunction(ChartType.MONTH);
	            }
	        });
		 
		 ImageButton quarterBtn = (ImageButton) findViewById(R.id.quarterBtn);
		 quarterBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	btnClickFunction(ChartType.THREE_MONTH);
	            }
	        });
		 
		 ImageButton halfYearBtn = (ImageButton) findViewById(R.id.halfYearBtn);
		 halfYearBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	btnClickFunction(ChartType.HALF_YEAR);
	            }
	        });
		 
		 ImageButton oneYearBtn = (ImageButton) findViewById(R.id.yearBtn);
		 oneYearBtn.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	btnClickFunction(ChartType.YEAR);
	            }
	        });
		 
		 ImageButton addToSelfCheckBtn = (ImageButton)findViewById(R.id.addToSelf);
		 addToSelfCheckBtn.setOnClickListener(new AddToSelfCheckFundBtnListener());
	}
	
	/**
	 * 重构绑定天数按钮所要用到的重复代码
	 * @param dayCount
	 */
	private void btnClickFunction(ChartType chartType){
		if(jjjzs != null){
			jjjzsChart.setChecked(false);
    		jjjzsChart.setChartType(chartType);
    		refleshHandler.sendEmptyMessage(JJJZ_CHART_REFLESH);
    	}
	}
	
	/**
	 * 显示基金简称和代码
	 */
	private void refleshBaseInfo(){
		TextView jjjcTextView = (TextView)findViewById(R.id.JjjzsActivity_jjNameTextView);
		jjjcTextView.setText(this.jjjc);
		
		TextView dmTextView = (TextView)findViewById(R.id.JjjzsActivity_jjdmTextView);
		dmTextView.setText(this.dm);
	}
	
	private class AddToSelfCheckFundBtnListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			ThreadPoolUtils.execute(new AddToSelfCheckFundRunnable());
		}
	}
	
	private class AddToSelfCheckFundRunnable implements Runnable{
		@Override
		public void run() {
			String accountId = InitFundReport.loginStatus.getAccountId();
			String url = NetWorkConfig.getAddSelfCheckFundUrl(accountId, dm);
			try{
				String json = HttpDownloader.downloadCompressedByte(url);
				AddSelfCheckStatus status = ModelFactory.getAddSelfCheckStatus(json);
				System.out.println(status.isSuccess());
				System.out.println(status.getDms());
				
				//加入自选失败
				if(status.isSuccess() == false){
					refleshHandler.sendEmptyMessage(ADD_SELF_CHECK_FUND_FAIL);
					return;
				}
				
				refleshHandler.sendEmptyMessage(ADD_SELF_CHECK_FUND_SUCCESS);
				InitFundReport.loginStatus.setSelfCheckJjDms(status.getDms());
			}
			catch(Exception ex){
				refleshHandler.sendEmptyMessage(NET_WORK_ERROR);
				System.out.println("请求数据失败！");
			}
		}
	}
}
