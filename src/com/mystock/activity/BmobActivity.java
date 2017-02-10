package com.mystock.activity;

import java.util.ArrayList;
import java.util.List;

import com.mystock.R;
import com.mystock.R.layout;
import com.mystock.entity.Person;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class BmobActivity extends Activity implements OnClickListener{
	private View imbg; 
	private Button btnLogin;
	private Button btnRegister;
	private EditText tvName;
	private TextView tvPassWord;
	private ImageView ivloading;
	private boolean flag=false;
	private BroadcastReceiver receiver;
	ConnectivityManager connectivityManager;
	NetworkInfo networkInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmob);
		connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);  
		networkInfo = connectivityManager.getActiveNetworkInfo(); 
		initReceiver();
		registerRCV();
		//初始化bmob
		Bmob.initialize(this, "48ea7cf30a88a1b4133739efb3754345");
		initView();
		netWorkInfo();
		setOnClickListener();

	}
	@Override
	protected void onDestroy() {
		  if(receiver!=null){  
	            unregisterReceiver(receiver);  
	        }  
		  finish();
		super.onDestroy();
	}

	private void registerRCV() {
		 IntentFilter mFilter = new IntentFilter();  
	       mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);  
	       registerReceiver(receiver, mFilter);  
		
	}

	private void initReceiver() {
		receiver=new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();  
				if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {  

					connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);  
					networkInfo = connectivityManager.getActiveNetworkInfo();    
					if(networkInfo != null && networkInfo.isAvailable()) {  

						/////////////网络连接  
						String name = networkInfo.getTypeName();  

						if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){  
							flag=true;
							setOnClickListener();
							Toast.makeText(getApplicationContext(), "wifi已连接", Toast.LENGTH_SHORT).show();
						}else if(networkInfo.getType()==ConnectivityManager.TYPE_ETHERNET){  
							/////有线网络  
							flag=true;
							setOnClickListener();
							Toast.makeText(getApplicationContext(), "有线网络已连接", Toast.LENGTH_SHORT).show();
						}else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){  
							/////////3g网络  
							flag=true;
							setOnClickListener();
							Toast.makeText(getApplicationContext(), "移动网络已连接", Toast.LENGTH_SHORT).show();
						}  
					} else {  
						////////网络断开  
						Toast.makeText(getApplicationContext(), "网络已断开", Toast.LENGTH_SHORT).show();
						
					}  
				}  
			}
		};
	}
	private void netWorkInfo() {

		if(networkInfo == null || !networkInfo.isAvailable()){  
			//当前无可用网络  
			Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_LONG).show();
			flag=false;
		}  
		else   
		{  
			//当前有可用网络
			flag=true;	
		}

	}
	private void setOnClickListener() {
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

	}
	private void initView() {
		btnLogin=(Button) findViewById(R.id.btn_login);
		btnRegister=(Button) findViewById(R.id.btn_register);
		tvName=(EditText) findViewById(R.id.et_account);
		tvPassWord=(EditText) findViewById(R.id.et_pwd);
		ivloading=(ImageView) findViewById(R.id.loading);
		imbg=findViewById(R.id.IM_bg);
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_login:
			if(flag){
				Animation anim = AnimationUtils.loadAnimation(this, R.drawable.rotate);
				anim.setRepeatCount(Animation.INFINITE);
				if(tvName.getText().toString().length()!=0&&tvPassWord.getText().length()!=0){
					ivloading.setVisibility(View.VISIBLE);
					imbg.setVisibility(View.VISIBLE);
					ivloading.startAnimation(anim);
					btnLogin.setEnabled(false);
					btnRegister.setEnabled(false);
					login();
				}else{
					Toast.makeText(this, "请输入登陆信息", Toast.LENGTH_SHORT).show();
				}

				break;
			}else{
				Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_LONG).show();
			}
		case R.id.btn_register:
			if(flag){
				if(tvName.getText().toString().length()!=0&&tvPassWord.getText().length()!=0){
					BmobQuery<Person> query = new BmobQuery<Person>();
					query.addWhereEqualTo("name", tvName.getText().toString());
					query.findObjects(new FindListener<Person>() {

						@Override
						public void done(List<Person> arg0, BmobException e) {
							Log.i("w", "返回的List<person>为"+arg0.toString());
							if(arg0.size()!=0){
								Toast.makeText(BmobActivity.this, "用户"+tvName.getText().toString()+"已存在",  Toast.LENGTH_SHORT).show();

							}else{
								//Log.i("w", e.toString());
								Person person=new Person(tvName.getText().toString(), tvPassWord.getText().toString());
								person.save(new SaveListener<String>() {

									@Override
									public void done(String arg0, BmobException e) {
										if(e==null){
											Toast.makeText(BmobActivity.this,"注册成功，正在自动登陆...",Toast.LENGTH_SHORT).show();
											login();
										}else{
											Toast.makeText(BmobActivity.this,"更新失败，请稍后重试",Toast.LENGTH_SHORT).show();
										}

									}
								});
							}							
						}
					});


				}else{
					Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
				}
				break;
			}else{
				Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_LONG).show();
			}
		}
	}

	private void login() {

		//--and条件1
		BmobQuery<Person> eq1 = new BmobQuery<Person>();
		eq1.addWhereEqualTo("name",tvName.getText().toString());
		//--and条件2
		BmobQuery<Person> eq2 = new BmobQuery<Person>();
		eq2.addWhereEqualTo("passWord",tvPassWord.getText().toString());
		//最后组装完整的and条件
		List<BmobQuery<Person>> andQuerys = new ArrayList<BmobQuery<Person>>();
		andQuerys.add(eq1);
		andQuerys.add(eq2);		
		//查询符合整个and条件的人
		BmobQuery<Person> query = new BmobQuery<Person>();
		query.and(andQuerys);
		query.findObjects(new FindListener<Person>() {
			@Override
			public void done(List<Person> object, BmobException e) {
				if(object!=null&&object.size()!=0){
					onSuccess();
				}else{
					Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
					ivloading.clearAnimation();
					ivloading.setVisibility(View.GONE);
					imbg.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(), "用户不存在或密码错误", Toast.LENGTH_SHORT).show();
					btnLogin.setEnabled(true);
					btnRegister.setEnabled(true);
				}
			}

		});     


	}
	protected void onSuccess() {
		Intent intent=new Intent();
		intent.setClass(this, MainActivity.class);
		this.startActivity(intent);
		finish();
	}


}
