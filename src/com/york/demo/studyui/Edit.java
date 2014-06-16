package com.york.demo.studyui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class Edit extends Activity {
	
	EditText mEdit;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
				
		mEdit = (OwnDefineEdit)findViewById(R.id.editText);
		mEdit.setHint("在这里输入...");
		
	
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Title mTitle = new Title(this);
		mTitle.sendToUpdateTitle("文本输入");
		mTitle.sendToUpdateProgressVisible(false);
	}
}
