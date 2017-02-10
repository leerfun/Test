package com.mystock.app;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mystock.entity.Data;


import android.app.Application;
import android.util.Log;


public class StockApp extends Application {
	private static StockApp app;
	private static RequestQueue queue;

	private  List<Data> stocks=new ArrayList<Data>();
	@Override
	public void onCreate() {		
		super.onCreate();
		app=this;
		queue=Volley.newRequestQueue(this);
		Log.i("fun", "´´½¨app");
		  x.Ext.init(this);
		
	}
	
	public static StockApp getApp() {
		return app;
	}
	public static void setApp(StockApp app) {
		StockApp.app = app;
	}
	public static RequestQueue getQueue() {
		return app.queue;
	}
	public static void setQueue(RequestQueue queue) {
		app.queue = queue;
		
	}
	public  List<Data> getStocks() {
		return stocks;
	}
	public  List<Data> addStocks(Data stock) {
		stocks.add(stock);
		return stocks;
	}
	
	
	
}
