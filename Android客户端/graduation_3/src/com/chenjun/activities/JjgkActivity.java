package com.chenjun.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.chenjun.R;
import com.chenjun.fund.model.Jjgk;
import com.chenjun.fund.model.ModelFactory;
import com.chenjun.network.HttpDownloader;
import com.chenjun.network.NetWorkConfig;
import com.chenjun.utils.StringUtils;
import com.chenjun.utils.ThreadPoolUtils;

public class JjgkActivity extends Activity{
	private static final int JJGK_RECEIVE_REFLESH = 1;
	
	private Jjgk jjgk;
	//private Thread downloadThread;								//下载基金走势的线程
	private Handler refleshHandler = new RefleshHandler();		//刷新Handler，用于用户按钮点击或数据到达时处理对应事件
	
	private String dm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.jjgkactivity);
		super.onCreate(savedInstanceState);
		
		dm = getIntent().getStringExtra("dm");
		
		if(jjgk == null){
			ThreadPoolUtils.execute(new DownloadRunnable(dm));
		}
	}
	
	/**
	 * 刷新Handler
	 * @author zet
	 *
	 */
	private class RefleshHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			if(JJGK_RECEIVE_REFLESH == msg.what){
				refleshInfo();
			}
		}
	}
	
	/**
	 * 数据请求线程具体函数
	 * @author zet
	 *
	 */
	private class DownloadRunnable implements Runnable{
		private String dm;
		
		public DownloadRunnable(String dm){
			this.dm = dm;
		}
		
		@Override
		public void run() {
			System.out.println("开始请求数据");
			
			String json;
			try {
				//json = HttpDownloader.download("http://60.176.46.221:8081/AndroidFundServer/jjgk.json?dm=000021");
				//json = HttpDownloader.download("http://androidfund.3322.org:8081/AndroidFundServer/jjgk.json?dm=000021");
				json = HttpDownloader.downloadCompressedByte(NetWorkConfig.getJjgkUrl(dm));
				System.out.println("数据请求成功！");
				
				//System.out.println(json);
				
				jjgk = ModelFactory.getJjgk(json);
				
				System.out.println("数据转换成功！");
				
				//发送对应事件给Handler
				refleshHandler.sendEmptyMessage(JJGK_RECEIVE_REFLESH);
			} catch (Exception e) {
				//下载失败处理，应再新建一个提示给用户的信息，询问是否重试
				//Toast.makeText(JjjzsActivity.this, "数据下载失败！", Toast.LENGTH_LONG).show();
				System.out.println("请求数据失败！");
			}
		}
	}
	
	private void refleshInfo(){
		if(jjgk == null){
			return;
		}
		
		//代码
		TextView dmTextView = (TextView)findViewById(R.id.Jjgk_dm_Value);
		setTextViewText(dmTextView, jjgk.getDm());
		
		//基金名称
		TextView jjmcTextView = (TextView)findViewById(R.id.Jjgk_jjmc_Value);
		setTextViewText(jjmcTextView, jjgk.getJjmc());
		
		//基金简称
		TextView jjjcTextView = (TextView)findViewById(R.id.Jjgk_jjjc_Value);
		setTextViewText(jjjcTextView, jjgk.getJjjc());
		
		//拼音简称
		TextView pyjcTextView = (TextView)findViewById(R.id.Jjgk_pyjc_Value);
		setTextViewText(pyjcTextView, jjgk.getPyjc());
		
		//基金类型
		TextView jjlxTextView = (TextView)findViewById(R.id.Jjgk_jjlx_Value);
		setTextViewText(jjlxTextView, jjgk.getJjlx());
		
		//基金管理人
		TextView jjglrTextView = (TextView)findViewById(R.id.Jjgk_jjglr_Value);
		setTextViewText(jjglrTextView, jjgk.getJjglr());
		
		//基金托管人
		TextView jjtgrTextView = (TextView)findViewById(R.id.Jjgk_jjtgr_Value);
		setTextViewText(jjtgrTextView, jjgk.getJjtgr());
		
		//管理费率
		TextView glflTextView = (TextView)findViewById(R.id.Jjgk_glfl_Value);
		setTextViewText(glflTextView, jjgk.getGlfl());
		
		//托管费率
		TextView tgflTextView = (TextView)findViewById(R.id.Jjgk_tgfl_Value);
		setTextViewText(tgflTextView, jjgk.getTgfl());
		
		//投资类型
		TextView tzlxTextView = (TextView)findViewById(R.id.Jjgk_tzlx_Value);
		setTextViewText(tzlxTextView, jjgk.getTzlx());
		
		//成立日期
		TextView clrqTextView = (TextView)findViewById(R.id.Jjgk_clrq_Value);
		setTextViewText(clrqTextView, jjgk.getClrq());
		
		//开放申购起始日
		TextView kfsgqsrTextView = (TextView)findViewById(R.id.Jjgk_kfsgqsr_Value);
		setTextViewText(kfsgqsrTextView, jjgk.getKfsgqsr());
		
		//开放赎回起始日
		TextView kfshqsrTextView = (TextView)findViewById(R.id.Jjgk_kfshqsr_Value);
		setTextViewText(kfshqsrTextView, jjgk.getKfshqsr());
		
		//单笔申购金额下限
		TextView dbsgjexxTextView = (TextView)findViewById(R.id.Jjgk_dbsgjexx_Value);
		setTextViewText(dbsgjexxTextView, jjgk.getDbsgjexx());
		
		//单笔赎回份额下限
		TextView dbshfexxTextView = (TextView)findViewById(R.id.Jjgk_dbshfexx_Value);
		setTextViewText(dbshfexxTextView, jjgk.getDbshfexx());
		
		//投资风格
		TextView tzfgTextView = (TextView)findViewById(R.id.Jjgk_tzfg_Value);
		setTextViewText(tzfgTextView, jjgk.getTzfg());
		
		//投资目标
		TextView tzmbTextView = (TextView)findViewById(R.id.Jjgk_tzmb_Value);
		setTextViewText(tzmbTextView, jjgk.getTzmb());
		
		//投资范围
		TextView tzfwTextView = (TextView)findViewById(R.id.Jjgk_tzfw_Value);
		setTextViewText(tzfwTextView, jjgk.getTzfw());
		
		//巨额赎回比例认定
		TextView jeshblrdTextView = (TextView)findViewById(R.id.Jjgk_jeshblrd_Value);
		setTextViewText(jeshblrdTextView, jjgk.getJeshblrd());
		
		//巨额赎回条款
		TextView jeshtkTextView = (TextView)findViewById(R.id.Jjgk_jeshtk_Value);
		setTextViewText(jeshtkTextView, jjgk.getJeshtk());
		
		//市场风险提示
		TextView scfxtsTextView = (TextView)findViewById(R.id.Jjgk_scfxts_Value);
		setTextViewText(scfxtsTextView, jjgk.getScfxts());
		
		//管理风险提示
		TextView glfxtsTextView = (TextView)findViewById(R.id.Jjgk_glfxts_Value);
		setTextViewText(glfxtsTextView, jjgk.getGlfxts());
		
		//技术风险提示
		TextView jsfxtsTextView = (TextView)findViewById(R.id.Jjgk_jsfxts_Value);
		setTextViewText(jsfxtsTextView, jjgk.getJsfxts());
		
		//费率提取标准
		TextView fltqbzTextView = (TextView)findViewById(R.id.Jjgk_fltqbz_Value);
		setTextViewText(fltqbzTextView, jjgk.getFltqbz());
		
		//基金发起人
		TextView jjfqrTextView = (TextView)findViewById(R.id.Jjgk_jjfqr_Value);
		setTextViewText(jjfqrTextView, jjgk.getJjfqr());
		
		//销售代理人
		TextView xsdlrTextView = (TextView)findViewById(R.id.Jjgk_xsdlr_Value);
		setTextViewText(xsdlrTextView, jjgk.getXsdlr());
		
		//投资策略
		TextView tzclTextView = (TextView)findViewById(R.id.Jjgk_tzcl_Value);
		setTextViewText(tzclTextView, jjgk.getTzcl());
		
		//基金终止条款
		TextView jjzztkTextView = (TextView)findViewById(R.id.Jjgk_jjzztk_Value);
		setTextViewText(jjzztkTextView, jjgk.getJjzztk());
		
		//业绩比较基准
		TextView yjbjjzTextView = (TextView)findViewById(R.id.Jjgk_yjbjjz_Value);
		setTextViewText(yjbjjzTextView, jjgk.getYjbjjz());
		
		//申购状态
		TextView sgztTextView = (TextView)findViewById(R.id.Jjgk_sgzt_Value);
		setTextViewText(sgztTextView, jjgk.getSgzt());
	}
	
	private void setTextViewText(TextView textView, String info){
		if(textView == null || info == null){
			return;
		}
		info = StringUtils.databaseStr2TextViewStr(info);
		textView.setText(StringUtils.addT(info));
	}
}
