package com.york.demo.studyui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/*
 * ������������Զ���һ��View��Ч���������滭һ�����Σ������϶����ž��Σ�Ҳ�����ƶ����Ρ�
 */
public class MyView extends View implements Runnable {
	
	private Paint mPaint;
	private float left = 0.0f;
	private float top = 0.0f;
	private float right = 200.0f;
	private float bottom = 200.0f;
	private GestureDetector gestureDetector;
	private int red = 200, green, blue;
	
	private Context mContext;
	
	public MyView(Context context) {
		super(context);
		mContext = context;        //��Activity�����������
		mPaint = new Paint();
		gestureDetector = new GestureDetector(context, onGestureListener);
		new Thread(this).start();
	}
	
	GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
				
		//ע�⣺����ļ����з���ֵ�ķ���һ����Ҫ����true������onScroll��onFling�¼����ᴥ��
		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(mContext, "onSingleTapUp", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(mContext, "onShowPress", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			//Toast.makeText(mContext, "onScroll", Toast.LENGTH_SHORT).show();
			left = left - arg2;
			right = right - arg2;
			top = top - arg3;
			bottom = bottom - arg3;
			
			return true;
		}
		
		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(mContext, "onLongPress", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(mContext, "onDown", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			left = 0.0f;
			top = 0.0f;
			right = 200.0f;
			bottom = 200.0f;
			return true;
		}
	};
	
	@Override
	public void onDraw(Canvas canvas) {   
        super.onDraw(canvas);   
                   
        mPaint.setColor(Color.rgb(red, green, blue));   
        mPaint.setStyle(Style.FILL); //�������   
        canvas.drawRect(left, top, right, bottom, mPaint); //���ƾ���   
           
        mPaint.setColor(Color.BLUE);   
        //canvas.drawText("���Ǳ���������", 10, 120, mPaint);
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getPointerCount()==2)   //��㴥��
		{
			float x0 = event.getX(0);
			float y0 = event.getY(0);
			float x1 = event.getX(1);
			float y1 = event.getY(1);
			
			if (x0 < x1)
			{
				left = x0;
				right = x1;
			}
			else
			{
				left = x1;
				right = x0;
			}
			if (y0 < y1)
			{
				top = y0;
				bottom = y1;
			}
			else
			{
				top = y1;
				bottom = y0;
			}
			return true;
		}
		else                              //���㴥��
		{
			return gestureDetector.onTouchEvent(event);
		}
	}
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted())
		{
			try
			{
				//red = (red + 1) % 255;
				green = (green + 1) % 255;
				blue = (blue + 2) % 255;
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}
}
