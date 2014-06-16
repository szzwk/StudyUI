package com.york.demo.studyui;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class List extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		Log.e("HUAN", "List onCreate");
		
		final MyListAdapter adapter = new MyListAdapter(this);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "选中了: " + ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
				adapter.setSelectedItem(arg2);
				adapter.notifyDataSetInvalidated();           //重新刷新填充ListView
				
			}
		});
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		Log.e("HUAN", "List onResume");
		
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("列表");
		mTitle.sendToUpdateProgressVisible(false);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.e("HUAN", "List onPause");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.e("HUAN", "List onDestroy");
	}
	
	/*
	 * 要实现一些自定义的功能则要重写BaseAdapter类了
	 * ListView绘制的过程如下：首先，系统在绘制ListView之前，将会先调用getCount方法来获取Item的个数。
	 * 之后每绘制一个 Item就会调用一次getView方法，在此方法内就可以引用事先定义好的xml来确定显示的效果
	 * 并返回一个View对象作为一个Item显示出来。
	 */
	private class MyListAdapter extends BaseAdapter {

		public MyListAdapter(Context context)
		{
			mContext = context;
			Log.e("HUAN", "1");
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			Log.e("HUAN", "2");
			return mStrings.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			Log.e("HUAN", "3");
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			Log.e("HUAN", "4");
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Log.e("HUAN", "5");
			TextView tv;
			if (convertView == null)
			{	
				Log.e("HUAN", "5 1");
				tv = (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
			}
			else
			{	
				Log.e("HUAN", "5 2");
				tv = (TextView)convertView;
			}
			tv.setText(mStrings[position]);
			if (position == selectedItem)
			{
				tv.setBackgroundColor(Color.RED);
				Toast.makeText(getBaseContext(), tv.getText(), Toast.LENGTH_SHORT).show();
			}
			else
			{
				tv.setBackgroundColor(Color.TRANSPARENT);
			}
			return tv;
		}
		
		public void setSelectedItem(int selectedItem)
		{
			this.selectedItem = selectedItem;
		}
		
		private Context mContext;
		
		private int selectedItem = -1;
		
	    private String[] mStrings = {
	            "北京", "上海", "广州", "深圳", "天津", "重庆", "苏州", 
	            "杭州", "无锡", "南京", "青岛", "大连", "武汉", "襄阳", 
	          "老河口", "佛山", "东莞", "南阳", "成都", "喀什", "长春" };   
	}	
}
