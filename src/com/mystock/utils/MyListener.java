package com.mystock.utils;

import android.view.View.OnClickListener;

public abstract class MyListener implements OnClickListener{
	
	private int position;
	
	
	public MyListener(int position) {
		super();
		this.position = position;
	}




}
