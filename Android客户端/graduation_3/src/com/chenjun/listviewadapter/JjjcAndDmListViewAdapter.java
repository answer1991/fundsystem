package com.chenjun.listviewadapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenjun.R;
import com.chenjun.activities.FundInfoActivityGroup;
import com.chenjun.activities.GoToFundInfoActivityGroup;

public class JjjcAndDmListViewAdapter extends BaseAdapter {
	private List<Map<String, Object>> mData;
	private LayoutInflater mInflater;
	private GoToFundInfoActivityGroup parentActivity;
	private Context context;
	
	public JjjcAndDmListViewAdapter(Context context, List<Map<String, Object>> mData, GoToFundInfoActivityGroup parentActivity){
		this.context = context;
		this.mData = mData;
		this.mInflater = LayoutInflater.from(context);
		this.parentActivity = parentActivity;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
        if (convertView == null) {
             
            holder=new ViewHolder();  
             
            convertView = mInflater.inflate(R.layout.fundbaseinfo_list_element, null);
            
            holder.jjjc = (TextView)convertView.findViewById(R.id.FundBaseInfo_element_Jjjc);
//            holder.jjjz = (TextView)convertView.findViewById(R.id.FundBaseInfo_element_Jjjz);
            holder.dm = (TextView)convertView.findViewById(R.id.FundBaseInfo_element_Jjdm);
//            holder.rq = (TextView)convertView.findViewById(R.id.FundBaseInfo_element_Rq);
//            holder.increase = (TextView)convertView.findViewById(R.id.FundBaseInfo_element_Increase);
            
            convertView.setTag(holder);
             
        }else {
             
            holder = (ViewHolder)convertView.getTag();
        }
        
        holder.jjjc.setText((String)mData.get(position).get("jjjc"));
//        holder.jjjz.setText(StringUtils.jzFormat((String)mData.get(position).get("jjjz")));
        holder.dm.setText((String)mData.get(position).get("dm"));
//        holder.rq.setText((String)mData.get(position).get("rq"));
//        holder.increase.setText(StringUtils.increaseFormat((String)mData.get(position).get("increase")));
        
        Intent intent = new Intent();
		intent.putExtra("jjjc", (String)mData.get(position).get("jjjc"));
		intent.putExtra("dm", (String)mData.get(position).get("dm"));
		intent.setClass(context, FundInfoActivityGroup.class);
        
        convertView.setOnClickListener(new ClickListener(intent));
        
        return convertView;
	}
	
	
	public final class ViewHolder{
		public TextView jjjc;				//基金简称
//		public TextView jjjz;				//基金净值,可能是净值，也可能是累计净值
		public TextView dm;					//基金代码
//		public TextView rq;					//拼音简称
//		public TextView increase;			//基金净值增长
	}
	
	private class ClickListener implements OnClickListener{
		private Intent intent;
		
		public ClickListener(Intent intent){
			this.intent = intent;
		}
		@Override
		public void onClick(View v) {
			parentActivity.handle(intent);
		}
	}
	
	public void addDate(List<Map<String, Object>> mapList){
		this.mData.addAll(mapList);
	}
}
