package com.york.demo.studyui;

import android.content.Context;
import android.content.Intent;

public class Title {
	
	private Context mContext;
	
	private final static String ACTION1 = "android.intent.studyui.title";
	private final static String ACTION2 = "android.intent.studyui.progress";
	private final static String ACTION3 = "android.intent.studyui.progressVisible";

	public Title(Context context)
	{
		mContext = context;
	}
	
	public void sendToUpdateTitle(String strTitle)
	{
		Intent intent = new Intent();
		intent.setAction(ACTION1);
		intent.putExtra("title", strTitle);
		mContext.sendBroadcast(intent);
	}
	
	public void sendToUpdateProgress(int intProgress)
	{
		Intent intent = new Intent();
		intent.setAction(ACTION2);
		intent.putExtra("progress", intProgress);
		mContext.sendBroadcast(intent);
	}
	
	public void sendToUpdateProgressVisible(boolean bVisible)
	{
		Intent intent = new Intent();
		intent.setAction(ACTION3);
		intent.putExtra("progressVisible", bVisible);
		mContext.sendBroadcast(intent);
	}
}
