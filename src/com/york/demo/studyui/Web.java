package com.york.demo.studyui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class Web extends Activity {
	
	Button mButton;
	EditText mEditText;
	WebView mWebView;
	Title mTitle;
	String webTitle = "WebView";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		mTitle = new Title(this);		
		mButton = (Button) findViewById(R.id.button);
		mEditText = (EditText) findViewById(R.id.editText);
		mWebView = (WebView) findViewById(R.id.webView);
		
		WebSettings webSettings = mWebView.getSettings();   //��WebView��������
		webSettings.setJavaScriptEnabled(true);             //֧��javascript
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);           //֧����ҳ��������
				
		mWebView.setWebViewClient(new WebViewClient() {
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)  //������Ӽ����ڵ�ǰbrowser����Ӧ���������¿�Android��ϵͳbrowser����Ӧ������
		    {   
		        view.loadUrl(url);
		        return true;   
		    }
		});
				
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			//������ҳ���صĽ�����
			public void onProgressChanged(WebView view, int newProgress) 
			{	
				mTitle.sendToUpdateProgress(newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			@Override
			//����Ӧ�ó���ı���title
			public void onReceivedTitle(WebView view, String title) 
			{
				webTitle = title;
				mTitle.sendToUpdateTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{
					String sUrl = mEditText.getText().toString();
					if (URLUtil.isNetworkUrl(sUrl) )
					{  
			    		//װ����ַ
			    		mWebView.loadUrl(sUrl);
					}
			    	else
			    	{
			    		mEditText.setText("������ַ��������������");
					}
				}
				catch (Exception e)
				{
					
				}
			}
		});
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		mTitle.sendToUpdateTitle(webTitle);
		mTitle.sendToUpdateProgressVisible(true);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
		{
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
