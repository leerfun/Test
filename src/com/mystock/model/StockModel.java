package com.mystock.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.mystock.app.StockApp;
import com.mystock.entity.Data;
import com.mystock.utils.GlobalData;
import com.mystock.utils.JSONParser;
import com.mystock.utils.UrlFactory;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;


public class StockModel {
	private Handler mHandler;
	public StockModel(Handler mHandler){
		this.mHandler = mHandler;
	}

	/**
	 * 通过一个股票代码查询其股票信息放进stocks集合中
	 * @param StockGid
	 * @param callback
	 */

	public void loadStockInfoByStockGid(final String StockGid,final StockListCallBack callback,final int a){



		RequestQueue queue=StockApp.getQueue();
		String url=UrlFactory.getStockInfoUrl(StockGid);
		Log.i("TAG", url);
		StringRequest request=new StringRequest(url,
				new Listener<String>() {
			private List<Data> stocks;

			public void onResponse(String response) {	
				try {
					JSONObject obj = new JSONObject(response);
					//如果传入的字段不包含type字符
					if(!StockGid.contains("type")){
						switch (a) {
						case GlobalData.NOMAL:
							JSONArray ary = obj.getJSONArray("result");
							Log.i("w", "获取JsonArray"+ary);
							Data data=JSONParser.parseData(ary);
							Log.i("w", "获得data数据"+data);								
							StockApp.getApp().addStocks(data);
							stocks=StockApp.getApp().getStocks();
							callback.OnStockListLoaded(stocks);
							break;
						case GlobalData.UNNOMAL:
							if(obj.getString("resultcode").equals("200")){									
								mHandler.sendEmptyMessage(GlobalData.RIGHTGIDCODE);
								JSONArray ary2 = obj.getJSONArray("result");
								Log.i("w", "获取JsonArray"+ary2);
								Data data2=JSONParser.parseData(ary2);
								Log.i("w", "获得data数据"+data2);								
								StockApp.getApp().addStocks(data2);
								callback.OnStockListLoaded(stocks);
								break;																	
							}else{
								mHandler.sendEmptyMessage(GlobalData.WRONGGIDCODE);
							}
							break;
						case GlobalData.REQUESTPICS:
							JSONArray ary2 = obj.getJSONArray("result");
							JSONObject object=new JSONObject(ary2.getString(0));
							JSONObject objectw=new JSONObject(object.getString("gopicture"));
								GlobalData.urls.clear();
								GlobalData.urls.add( (String) objectw.get("minurl"));
								GlobalData.urls.add( (String) objectw.get("dayurl"));
								GlobalData.urls.add( (String) objectw.get("weekurl"));
								GlobalData.urls.add( (String) objectw.get("monthurl"));
								Log.i("w", "Json解析min"+GlobalData.urls.get(0)+GlobalData.urls.get(1)+GlobalData.urls.get(2));

							
							
							callback.OnStockListLoaded(stocks);
							break;
						}
					}else{
						JSONObject obj1=new JSONObject(response);
						JSONObject obj2=new JSONObject(obj1.getString("result"));
						Data data=new Data();
						data.setName(obj2.getString("name"));
						data.setNowPri(obj2.getString("nowpri"));
						data.setIncrease(obj2.getString("increase"));
						data.setIncrePer(obj2.getString("increPer"));						
						StockApp.getApp().addStocks(data);
						List<Data>stocks=StockApp.getApp().getStocks();
						callback.OnStockListLoaded(stocks);
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

		},

				new ErrorListener() {

			public void onErrorResponse(VolleyError error) {
				mHandler.sendEmptyMessage(GlobalData.DOWNLOADFAILD);
				//Toast.makeText(getActivity(), "dd", Toast.LENGTH_SHORT).show();

			}
		});
		queue.add(request);
		Log.i("fun", "网络请求已发送");
	}

}
