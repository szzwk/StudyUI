package com.york.demo.studyui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

public class OwnDefineEdit extends EditText {

	private Paint mPaint;
	
	public OwnDefineEdit(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{	
		int lineHeight = this.getLineHeight(); 
		int topPadding = this.getPaddingTop();
		int leftPadding = this.getPaddingLeft();
		float textSize = getTextSize();
		
		setGravity(Gravity.LEFT|Gravity.TOP);   //文本框里面的文字朝左朝上对齐。若不设置，则当文本框很大的时候，文字默认是现在在文本框的中间的，很不好看
		int y = (int)(topPadding + textSize);
		
		for(int i=0; i<getLineCount(); i++) 
		{
			canvas.drawLine(leftPadding, y + 2, getRight() - leftPadding, y + 2, mPaint);
			y += lineHeight;
		}
		canvas.translate(0, 0);
		
		super.onDraw(canvas);

	}
}
