package com.york.demo.studyui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class StudyUI extends TabActivity implements OnClickListener {
	
	TabHost mTabHost;
	MyReceiver    receiver;
	final static String ACTION1 = "android.intent.studyui.title";
	final static String ACTION2 = "android.intent.studyui.progress";
	final static String ACTION3 = "android.intent.studyui.progressVisible";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);	
        setContentView(R.layout.main);
        
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();     //这里要注册广播
        filter.addAction(ACTION1);
        filter.addAction(ACTION2);
        filter.addAction(ACTION3);
        registerReceiver(receiver, filter);
        
      //取得TabHost对象
		mTabHost = getTabHost();
		
		/* 为TabHost添加标签 */
		//新建一个newTabSpec(newTabSpec)
		//设置其标签和图标(setIndicator)
		//设置内容(setContent)
	    
	    TabHost.TabSpec spec = mTabHost.newTabSpec("ListView列表");
	    spec.setContent(new Intent(this, List.class));
	    spec.setIndicator("列表", getResources().getDrawable(R.drawable.img1));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("九宫格");
	    spec.setContent(new Intent(this, Grid.class));
	    spec.setIndicator("九宫格", getResources().getDrawable(R.drawable.img2));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("文本输入框");
	    spec.setContent(new Intent(this, Edit.class));
	    spec.setIndicator("文本输入", getResources().getDrawable(R.drawable.img3));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("WebView");
	    spec.setContent(new Intent(this, Web.class));
	    spec.setIndicator("webview", getResources().getDrawable(R.drawable.img4));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("自定义组件");
	    spec.setContent(new Intent(this, Component.class));
	    spec.setIndicator("自定义组件", getResources().getDrawable(R.drawable.img5));
	    mTabHost.addTab(spec);
	    
	    //设置TabHost的背景颜色
	    //mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	    //设置TabHost的背景图片资源
	    //mTabHost.setBackgroundResource(R.drawable.bg0);
	    
	    //设置当前显示哪一个标签
	    mTabHost.setCurrentTab(0);
	    
	    //标签切换事件处理，setOnTabChangedListener 
	    mTabHost.setOnTabChangedListener(new OnTabChangeListener()
	    {
	    	// TODO Auto-generated method stub
            @Override
            public void onTabChanged(String tabId) 
            {            	
  	    	  	Dialog dialog = new AlertDialog.Builder(StudyUI.this)
  	    	  			.setTitle("提示")
  	    	  			.setMessage("当前选中：" + tabId + "选项卡")
  	    	  			.setPositiveButton("确定",
  	    	  			new DialogInterface.OnClickListener() 
  	    	  			{
  	    	  				public void onClick(DialogInterface dialog, int whichButton)
  	    	  				{
  	    	  					dialog.cancel();
  	    	  				}
  	    	  			}).create();//创建按钮
	    	  
  	    	  	//dialog.show();
            }            
        });
	}
    
    @Override
    public void onClick(View v) {
    	int curIndex = mTabHost.getCurrentTab();
    	Log.e("HUAN", "Tab onClick " + mTabHost.getId());
    }
    
    public class MyReceiver extends BroadcastReceiver     //广播接收器定义在Activity的内部，便于引用Activity中的控件
    {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(ACTION1))
			{
				String title = (String)intent.getExtras().getString("title");
				setTitle(title);
			}
			
			if (intent.getAction().equals(ACTION2))   //加载网页时候的进度条
			{
				int progress = intent.getExtras().getInt("progress");
				setProgress(progress);
			}
			
			if (intent.getAction().equals(ACTION3))   //加载网页时候的进度条
			{
				boolean visible = intent.getExtras().getBoolean("progressVisible");
				setProgressBarVisibility(visible);
			}
		}
    }
}