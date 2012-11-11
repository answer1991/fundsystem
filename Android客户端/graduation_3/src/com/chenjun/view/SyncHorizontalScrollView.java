package com.chenjun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.chenjun.R;

public class SyncHorizontalScrollView extends HorizontalScrollView {
	private ViewGroup listView;
	private HorizontalScrollView headView;

	public SyncHorizontalScrollView(Context context) {
		super(context);
	}

	public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setSyncView(ViewGroup view) {
		this.listView = view;
	}
	
	public void setHeadView(HorizontalScrollView headView){
		this.headView = headView;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (null != listView) {
			for (int i = 0; i < listView.getChildCount(); i++) {
				View childView = listView.getChildAt(i);
				View scrollView = childView
						.findViewById(R.id.fundbaseinfo_list_item_scrollView);
				scrollView.scrollTo(l, t);
			}
		}
		if(null != headView){
			headView.scrollTo(l, t);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
