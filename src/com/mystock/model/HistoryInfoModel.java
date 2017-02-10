package com.mystock.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.mystock.app.StockApp;

import android.util.Log;

public class HistoryInfoModel {
	
	
	public void parseUrl(final String url,final HistoryCallBack callback){
		 
	RequestQueue queue=StockApp.getQueue();
		StringRequest request=new StringRequest(url, 
				new Listener<String>() {

			
			public void onResponse(String response) {
				 ArrayList<String>dates=new ArrayList<String>();
				  ArrayList<Double>prices=new ArrayList<Double>();
				try{
					JSONObject obj=new JSONObject(response);
					JSONArray ary = obj.getJSONArray("record");
					for(int i=0;i<ary.length();i++){
						JSONArray ary2=(JSONArray) ary.get(i);
						String date=ary2.getString(0);
						double price=ary2.getDouble(3);
						prices.add(price);
						dates.add(date);
						Log.i("history", "请求jsonarry中数组的第一位为"+date+"第三位为"+price);
					}
					callback.OnDateListLoaded(dates);
					callback.OnPriceListLoaded(prices);
					
				}catch(Exception e){

				}

			}
		},
				new ErrorListener() {

		
			public void onErrorResponse(VolleyError error) {
				Log.i("w", "获取历史消息出错");

			}
		});
		queue.add(request);
	}

}
