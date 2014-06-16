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
				Toast.makeText(getBaseContext(), "ѡ����: " + ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
				adapter.setSelectedItem(arg2);
				adapter.notifyDataSetInvalidated();           //����ˢ�����ListView
				
			}
		});
    }
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		Log.e("HUAN", "List onResume");
		
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("�б�");
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
	 * Ҫʵ��һЩ�Զ���Ĺ�����Ҫ��дBaseAdapter����
	 * ListView���ƵĹ������£����ȣ�ϵͳ�ڻ���ListView֮ǰ�������ȵ���getCount��������ȡItem�ĸ�����
	 * ֮��ÿ����һ�� Item�ͻ����һ��getView�������ڴ˷����ھͿ����������ȶ���õ�xml��ȷ����ʾ��Ч��
	 * ������һ��View������Ϊһ��Item��ʾ������
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
	            "����", "�Ϻ�", "����", "����", "���", "����", "����", 
	            "����", "����", "�Ͼ�", "�ൺ", "����", "�人", "����", 
	          "�Ϻӿ�", "��ɽ", "��ݸ", "����", "�ɶ�", "��ʲ", "����" };   
	}	
}
