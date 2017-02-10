

package com.mystock.activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mystock.R;
import com.mystock.app.StockApp;
import com.mystock.entity.Data;
import com.mystock.fragment.Fragment1;
import com.mystock.fragment.Fragment2;
import com.mystock.model.StockListCallBack;
import com.mystock.model.StockModel;
import com.mystock.utils.UrlFactory;



public class MainActivity extends FragmentActivity {
	private ViewPager viewPager;
	private ArrayList<Fragment>fragments;
	private MainPagerAdapter pagerAdapter;
	private TextView tvshangz;
	private TextView tvshenz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.w("mainactvtyOncreate", "执行oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		setPageradApter();
		Log.d("设置pager", "setpageradapter");
		
		
	}
	
	/**
	 * 设置pageradapter
	 */
	private void setPageradApter() {
		fragments=new ArrayList<Fragment>();
		fragments.add(new Fragment1());
		fragments.add(new Fragment2());
		Log.i("w", "添加fragment1");
		pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		Log.i("适配器", "viewpager设置适配器完成");
	}

	/**
	 * 声明控件
	 */
	private void setView() {
		viewPager=(ViewPager) findViewById(R.id.viewPager);
		Log.i("找到view", "找到viewpager");
		tvshangz=(TextView) findViewById(R.id.TVTitleHu);
		tvshenz=(TextView) findViewById(R.id.TVTitleShen);
		
		
		
		Thread  thread=new Thread(new Runnable() {
			public void run() {
				String urlShang=UrlFactory.getStockInfoUrl("&type=0");
				String urlShen=UrlFactory.getStockInfoUrl("&type=1");
				String[]tittleString={urlShang,urlShen};
				int a=0;
				while(a<2){
				RequestQueue queue=StockApp.getQueue();
				StringRequest request=new StringRequest(tittleString[a], 
						new Listener<String>() {

							@Override
							public void onResponse(String response) {
								JSONObject obj1;
								try {
									obj1 = new JSONObject(response);
									JSONObject obj2=new JSONObject(obj1.getString("result"));
									if(obj2.getString("name").equals("上证指数")){
										tvshangz.setText(" 沪 "+obj2.getString("nowpri"));
										if(obj2.getString("increase").contains("-")){
											tvshangz.setTextColor(0xFF00FF00);
										}else{
											tvshangz.setTextColor(0xFFff0000);
										}
									}else{
										tvshenz.setText(" 深 "+obj2.getString("nowpri"));
										if(obj2.getString("increase").contains("-")){
											tvshenz.setTextColor(0xFF00FF00);
										}else{
											tvshenz.setTextColor(0xFFff0000);
										}
									}
								} catch (Exception e) {
									
									e.printStackTrace();
								}
								
							}
						}, new ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {
								Log.i("w", "初始化失败");
							}
						});
						a++;
						queue.add(request);
			}
			}
		});
		thread.start();
		
	}


	/**
	 * 自定义viewpager适配器类
	 * @author Administrator
	 *
	 */
	class MainPagerAdapter extends FragmentPagerAdapter{
		public MainPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int p) {
			return fragments.get(p);
		}
		@Override
		public int getCount() {
			return fragments.size();
		}
	}


}
