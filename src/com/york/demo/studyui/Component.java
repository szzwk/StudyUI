package com.york.demo.studyui;

import android.app.Activity;
import android.os.Bundle;

public class Component extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		MyView mView = new MyView(this);   //传递上下文从Activity到View
		setContentView(mView);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("自定义组件");
		mTitle.sendToUpdateProgressVisible(false);
	}
}
