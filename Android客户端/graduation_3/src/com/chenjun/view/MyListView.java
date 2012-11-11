package com.chenjun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView {
	
	private ListView relateListView;
	
	public MyListView(Context context) {
		super(context);
	}
	
	public MyListView(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	public MyListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	public void setRelateList(ListView listView){
		this.relateListView = listView;
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		relateListView.scrollTo(l, t);
		System.out.println(111);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
