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
		
		WebSettings webSettings = mWebView.getSettings();   //对WebView进行设置
		webSettings.setJavaScriptEnabled(true);             //支持javascript
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);           //支持网页内容缩放
				
		mWebView.setWebViewClient(new WebViewClient() {
			
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)  //点击链接继续在当前browser中响应，而不是新开Android的系统browser中响应该链接
		    {   
		        view.loadUrl(url);
		        return true;   
		    }
		});
				
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			//设置网页加载的进度条
			public void onProgressChanged(WebView view, int newProgress) 
			{	
				mTitle.sendToUpdateProgress(newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			@Override
			//设置应用程序的标题title
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
			    		//装载网址
			    		mWebView.loadUrl(sUrl);
					}
			    	else
			    	{
			    		mEditText.setText("输入网址错误，请重新输入");
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
