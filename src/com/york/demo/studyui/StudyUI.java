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
        IntentFilter filter = new IntentFilter();     //����Ҫע��㲥
        filter.addAction(ACTION1);
        filter.addAction(ACTION2);
        filter.addAction(ACTION3);
        registerReceiver(receiver, filter);
        
      //ȡ��TabHost����
		mTabHost = getTabHost();
		
		/* ΪTabHost��ӱ�ǩ */
		//�½�һ��newTabSpec(newTabSpec)
		//�������ǩ��ͼ��(setIndicator)
		//��������(setContent)
	    
	    TabHost.TabSpec spec = mTabHost.newTabSpec("ListView�б�");
	    spec.setContent(new Intent(this, List.class));
	    spec.setIndicator("�б�", getResources().getDrawable(R.drawable.img1));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("�Ź���");
	    spec.setContent(new Intent(this, Grid.class));
	    spec.setIndicator("�Ź���", getResources().getDrawable(R.drawable.img2));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("�ı������");
	    spec.setContent(new Intent(this, Edit.class));
	    spec.setIndicator("�ı�����", getResources().getDrawable(R.drawable.img3));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("WebView");
	    spec.setContent(new Intent(this, Web.class));
	    spec.setIndicator("webview", getResources().getDrawable(R.drawable.img4));
	    mTabHost.addTab(spec);
	    
	    spec = mTabHost.newTabSpec("�Զ������");
	    spec.setContent(new Intent(this, Component.class));
	    spec.setIndicator("�Զ������", getResources().getDrawable(R.drawable.img5));
	    mTabHost.addTab(spec);
	    
	    //����TabHost�ı�����ɫ
	    //mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	    //����TabHost�ı���ͼƬ��Դ
	    //mTabHost.setBackgroundResource(R.drawable.bg0);
	    
	    //���õ�ǰ��ʾ��һ����ǩ
	    mTabHost.setCurrentTab(0);
	    
	    //��ǩ�л��¼�����setOnTabChangedListener 
	    mTabHost.setOnTabChangedListener(new OnTabChangeListener()
	    {
	    	// TODO Auto-generated method stub
            @Override
            public void onTabChanged(String tabId) 
            {            	
  	    	  	Dialog dialog = new AlertDialog.Builder(StudyUI.this)
  	    	  			.setTitle("��ʾ")
  	    	  			.setMessage("��ǰѡ�У�" + tabId + "ѡ�")
  	    	  			.setPositiveButton("ȷ��",
  	    	  			new DialogInterface.OnClickListener() 
  	    	  			{
  	    	  				public void onClick(DialogInterface dialog, int whichButton)
  	    	  				{
  	    	  					dialog.cancel();
  	    	  				}
  	    	  			}).create();//������ť
	    	  
  	    	  	//dialog.show();
            }            
        });
	}
    
    @Override
    public void onClick(View v) {
    	int curIndex = mTabHost.getCurrentTab();
    	Log.e("HUAN", "Tab onClick " + mTabHost.getId());
    }
    
    public class MyReceiver extends BroadcastReceiver     //�㲥������������Activity���ڲ�����������Activity�еĿؼ�
    {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(ACTION1))
			{
				String title = (String)intent.getExtras().getString("title");
				setTitle(title);
			}
			
			if (intent.getAction().equals(ACTION2))   //������ҳʱ��Ľ�����
			{
				int progress = intent.getExtras().getInt("progress");
				setProgress(progress);
			}
			
			if (intent.getAction().equals(ACTION3))   //������ҳʱ��Ľ�����
			{
				boolean visible = intent.getExtras().getBoolean("progressVisible");
				setProgressBarVisibility(visible);
			}
		}
    }
}