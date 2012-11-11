package com.chenjun.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SimpleAdapter;

import com.chenjun.R;
import com.chenjun.fund.model.Jjcf;
import com.chenjun.fund.model.ModelFactory;
import com.chenjun.network.HttpDownloader;
import com.chenjun.network.NetWorkConfig;
import com.chenjun.utils.StringUtils;
import com.chenjun.utils.ThreadPoolUtils;

public class JjcfActivity extends ListActivity {

	private static final String[] itemNames = new String[] { "count", "ggrqKey",
			"ggrqValue", "cfrqKey", "cfrqValue", "cfqjzKey", "cfqjzValue",
			"cfhjzKey", "cfhjzValue", "cfblKey", "cfblValue" };

	private static final int[] itemIds = new int[] { R.id.Jjcf_count,
			R.id.Jjcf_ggrq_Key, R.id.Jjcf_ggrq_Value, R.id.Jjcf_cfrq_Key,
			R.id.Jjcf_cfrq_Value, R.id.Jjcf_cfqjz_Key, R.id.Jjcf_cfqjz_Value,
			R.id.Jjcf_cfhjz_Key, R.id.Jjcf_cfhjz_Value, R.id.Jjcf_cfbl_Key,
			R.id.Jjcf_cfbl_Value };

	private static final int JJCF_RECEIVE_REFLESH = 1;

	private List<Jjcf> jjcfs;
//	private Thread downloadThread; // 下载基金分红的线程
	private Handler refleshHandler = new RefleshHandler(); // 刷新Handler，用于用户按钮点击或数据到达时处理对应事件
	
	private String dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jjcfactivity);
		
		dm = getIntent().getStringExtra("dm");

		if (jjcfs == null) {
			ThreadPoolUtils.execute(new DownloadRunnable(dm));
		}
	}

	/**
	 * 数据请求线程具体函数
	 * 
	 * @author zet
	 * 
	 */
	private class DownloadRunnable implements Runnable {
		private String dm;
		
		public DownloadRunnable(String dm){
			this.dm = dm;
		}
		
		@Override
		public void run() {
			System.out.println("开始请求数据");

			String json;
			try {
				json = HttpDownloader
						.downloadCompressedByte(NetWorkConfig.getJjcfUrl(dm));
				System.out.println("数据请求成功！");

				// System.out.println(json);

				jjcfs = ModelFactory.getJjcfs(json);

				System.out.println("数据转换成功！");

				// 发送对应事件给Handler
				refleshHandler.sendEmptyMessage(JJCF_RECEIVE_REFLESH);
			} catch (Exception e) {
				// 下载失败处理，应再新建一个提示给用户的信息，询问是否重试
				System.out.println("请求数据失败！");
			}
		}
	}

	/**
	 * 刷新Handler
	 * 
	 * @author zet
	 * 
	 */
	private class RefleshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (JJCF_RECEIVE_REFLESH == msg.what) {
				refleshListView();
			}
		}
	}

	private void refleshListView() {
		setListAdapter(new SimpleAdapter(this, getData(),
				R.layout.jjcf_list_element, itemNames, itemIds));
		// this.getListView().postInvalidate();
	}

	private List<Map<String, Object>> getData() {
		if (jjcfs == null) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map;

		if (jjcfs.size() == 0) {
			map = new HashMap<String, Object>();
			map.put("count", getString(R.string.JjcfActivity_noCf_HelpInfo_TextView));
			list.add(map);
		}

		else {
			for (int i = 0; i < jjcfs.size(); i++) {
				map = new HashMap<String, Object>();
				Jjcf jjcf = jjcfs.get(i);

				map.put("count", "第" + (i + 1) + "次拆分");
				
				//公告日期
				map.put("ggrqKey", getString(R.string.JjcfActivity_ggrq_Key_TextView));
				map.put("ggrqValue", StringUtils.databaseStr2TextViewStr(jjcf.getGgrq()));
				
				//拆分日期
				map.put("cfrqKey", getString(R.string.JjcfActivity_cfrq_Key_TextView));
				map.put("cfrqValue", StringUtils.databaseStr2TextViewStr(jjcf.getCfrq()));
				
				//拆分前净值
				map.put("cfqjzKey", getString(R.string.JjcfActivity_cfqjz_Key_TextView));
				map.put("cfqjzValue", StringUtils.stringLenthFormat(jjcf.getCfqjz(), 6, '0'));
				
				//拆分后净值
				map.put("cfhjzKey", getString(R.string.JjcfActivity_cfhjz_Key_TextView));
				map.put("cfhjzValue", StringUtils.stringLenthFormat(jjcf.getCfhjz(), 6, '0'));
				
				//拆分比例
				map.put("cfblKey", getString(R.string.JjcfActivity_cfbl_Key_TextView));
				map.put("cfblValue", StringUtils.stringLenthFormat(jjcf.getCfbl(), 6, '0'));
				
				list.add(map);
			}
		}

		return list;
	}
}
