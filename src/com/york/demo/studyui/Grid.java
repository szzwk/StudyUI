package com.york.demo.studyui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Grid extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);	
		
		Log.e("HUAN", "Grid onCreate");
		
		GridView gridview = (GridView) findViewById(R.id.gridview);     
        gridview.setAdapter(new ImageAdapter(this));  
        gridview.setBackgroundResource(R.drawable.bg0);  

        gridview.setOnItemClickListener(new OnItemClickListener() {
        	
        	@Override
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)  
            {
        		Toast.makeText(Grid.this, "你选择了" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();  
            }

        });  
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		Log.e("HUAN", "Grid onResume");
		
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("九宫格");
		mTitle.sendToUpdateProgressVisible(false);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.e("HUAN", "Grid onPause");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.e("HUAN", "Grid onDestroy");
	}
	
	private class ImageAdapter extends BaseAdapter 
	{
		public ImageAdapter(Context context)
		{
			mContext = context;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageIds.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView;  
	        if (convertView == null)  
	        {
	        	// 给ImageView设置资源  
	            imageView = new ImageView(mContext);  
	            // 设置布局 图片120×120显示  
	            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));  
	            // 设置显示比例类型  
	            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
	         }  
	         else 
	         {  
	            imageView = (ImageView) convertView;  
	         }   
	         imageView.setImageResource(mImageIds[position]);  

	         return imageView;  
		}
		
		private Context mContext;
		
		private Integer[] mImageIds = 
		{
			R.drawable.sample_thumb_0,
			R.drawable.sample_thumb_1,
			R.drawable.sample_thumb_2,
			R.drawable.sample_thumb_3,
			R.drawable.sample_thumb_4,
			R.drawable.sample_thumb_5,
			R.drawable.sample_thumb_6,
			R.drawable.sample_thumb_7,
			R.drawable.sample_thumb_0,
			R.drawable.sample_thumb_1,
			R.drawable.sample_thumb_2,
			R.drawable.sample_thumb_3,
			R.drawable.sample_thumb_4,
			R.drawable.sample_thumb_5,
			R.drawable.sample_thumb_6,
			R.drawable.sample_thumb_7
		};
	}
}