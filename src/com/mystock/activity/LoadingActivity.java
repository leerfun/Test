package com.mystock.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.mystock.R;
import com.mystock.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0){
					Intent intent=new Intent(LoadingActivity.this,BmobActivity.class);
					LoadingActivity.this.startActivity(intent);
				}
				super.handleMessage(msg);
			}
		};
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
				
			}
		}, 1000);
	}

	

	
}
