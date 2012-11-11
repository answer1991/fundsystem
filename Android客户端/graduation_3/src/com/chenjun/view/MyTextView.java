package com.chenjun.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int availableWidth = this.getWidth() - this.getPaddingLeft()
				- this.getPaddingRight();
		int availableHeight = this.getHeight() - this.getPaddingTop()
				- this.getPaddingBottom() ;
		if(this.getText().length() > 0){
			int textLength = this.getText().length();
			float textSize = availableWidth / textLength;
			
			if(textSize > availableHeight){
				textSize = availableHeight;
			}
			//System.out.println(availableHeight);
			//System.out.println(textSize);
			Paint paint = this.getPaint();
			paint.setTextSize(textSize);
		}
		super.onDraw(canvas);
	}

}
