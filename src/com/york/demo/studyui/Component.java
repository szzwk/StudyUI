package com.york.demo.studyui;

import android.app.Activity;
import android.os.Bundle;

public class Component extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		MyView mView = new MyView(this);   //���������Ĵ�Activity��View
		setContentView(mView);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("�Զ������");
		mTitle.sendToUpdateProgressVisible(false);
	}
}
