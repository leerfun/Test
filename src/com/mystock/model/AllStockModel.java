package com.mystock.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mystock.R;
import com.mystock.app.StockApp;
import com.mystock.entity.Data2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AllStockModel {	

	//7.沪股列表
	public  void getRequest(int a,final ListData2CallBack callback){

		String result =null;
		String url ="http://web.juhe.cn:8080/finance/stock/shall?key=2f28ecace171de728951e527efae2e19&page="+a+"";//请求接口地址
		//		Map params = new HashMap();//请求参数
		//		params.put("key",APPKEY);//您申请的APPKEY
		//		params.put("page",'"'+a+'"');//第几页,每页20条数据,默认第1页
		RequestQueue queue=StockApp.getQueue();
		StringRequest request=new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					ArrayList<Data2> list=new ArrayList<Data2>();
					JSONObject object=new JSONObject();
					object = new JSONObject(response);
					if(object.getInt("error_code")==0){
						JSONObject obj=(JSONObject) object.get("result");
						JSONArray ary = obj.getJSONArray("data");
						for (int i = 0; i < ary.length(); i++) {
							JSONObject obj2=(JSONObject) ary.get(i);
							Data2 data=new Data2(obj2.getString("name"), obj2.getString("pricechange"),
									obj2.getString("buy"), obj2.getString("sell"));
							list.add(data);
						}
						callback.OnData2ListLoaded(list);
					}else{
						//System.out.println(object.get("error_code")+":"+object.get("reason"));
						Log.i("listview", "listview请求网络发生错误");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("listview", "网络请求错误");

			}
		});
		queue.add(request);
	}
}
