package com.mystock.fragment;


import java.util.List;

import com.mystock.R;
import com.mystock.activity.RotateCardActivity;
import com.mystock.activity.StockActivity;
import com.mystock.adapter.GridViewAdapter;
import com.mystock.app.StockApp;
import com.mystock.dao.DataHelper;
import com.mystock.entity.UserInfo;
import com.mystock.entity.Data;
import com.mystock.model.StockListCallBack;
import com.mystock.model.StockModel;
import com.mystock.utils.GlobalData;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment1 extends Fragment {

	private  GridView gridView;
	private GridViewAdapter gvAdapter;
	private StockModel stockModel;
	private Boolean flag=true;
	private	static List<UserInfo> userInfos;
	//public static final int OK=1;
	private int a=0;	
	private ImageButton btSet;
	private ImageButton btAdd;
	private ImageButton btLucky;
	private ImageButton btDelete;
	private DataHelper DBHelper;
	private AlertDialog alertDialog;
	private LinearLayout llloading;
	List<Data> stocks = StockApp.getApp().getStocks();
	private Handler mHander=new Handler(){
		@Override
		public void handleMessage(Message msg) {	
			switch (msg.what) {
			case GlobalData.DOWNLOADFAILD:
				Toast.makeText(getActivity(), "��������ȷ�Ĺ�Ʊ����", Toast.LENGTH_LONG).show();
				break;
			case GlobalData.WRONGGIDCODE:
				Toast.makeText(getActivity(), "��������ȷ�Ĺ�Ʊ����", Toast.LENGTH_LONG).show();
				break;

			case GlobalData.RIGHTGIDCODE:
				Toast.makeText(getActivity(), "�����ͳɹ�", Toast.LENGTH_SHORT).show();
				alertDialog.dismiss();// �Ի�����ʧ
				gvAdapter.notifyDataSetChanged();
				Log.i("gvAdapter", "gvadapter��"+gvAdapter.hashCode());
				break;
			}
			super.handleMessage(msg);

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment1, null);
		gridView=(GridView) view.findViewById(R.id.GvMyStock);
		btSet=(ImageButton) view.findViewById(R.id.ibSet);
		btAdd=(ImageButton) view.findViewById(R.id.ibAdd);
		btDelete=(ImageButton) view.findViewById(R.id.ib_delete);
		btLucky=(ImageButton) view.findViewById(R.id.ib_lucky);
		llloading=(LinearLayout) view.findViewById(R.id.llLoading);
		DBHelper=new DataHelper(getActivity());
		Log.i("w", "dbheler="+DBHelper);		
		stockModel=new StockModel(mHander);
		btAdd.setEnabled(false);
		btSet.setEnabled(false);
		btAdd.setVisibility(View.GONE);
		btDelete.setVisibility(View.GONE);
		btLucky.setVisibility(View.GONE);
		setStocks();		
		Log.i("w", "���з���setStocks");
		setListener();
		return view;
	}


	/**
	 * ���ü�������
	 */
	private void setListener() {
		/**
		 * Ϊgridview���õ������
		 */
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Data data=StockApp.getApp().getStocks().get(position);
				if(data.getName().contains("��֤")||data.getName().contains("��֤")){
					Toast.makeText(getActivity(), "����������δ���Ŵ�������", Toast.LENGTH_SHORT).show();
				}else{
				//String gidName=data.getGid();
				Intent intent=new Intent(getActivity(),StockActivity.class);
//				intent.putExtra("data", gidName);
				intent.putExtra("int",position);
				startActivity(intent);
				}
			}
		});
		/**
		 * ����fragment�İ�ť����
		 * ������ð�ťʱִ�ж�������ʾ��ɾ����ť����Ӱ�ť
		 */
		btSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {			
				AnimationSet animationset=new AnimationSet(true);
				TranslateAnimation alphaAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF,1f,
						Animation.RELATIVE_TO_SELF,0f);
				TranslateAnimation alphaAnimation2=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF,5f,
						Animation.RELATIVE_TO_SELF,0f);
				TranslateAnimation alphaAnimation3=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF, 0f,
						Animation.RELATIVE_TO_SELF,3f,
						Animation.RELATIVE_TO_SELF,0f);
				alphaAnimation.setDuration(300);//add
				alphaAnimation2.setDuration(300);//lucky
				alphaAnimation3.setDuration(300);//delete
				if(flag){	
				btDelete.setVisibility(View.VISIBLE);
				btAdd.setVisibility(View.VISIBLE);
				btLucky.setVisibility(View.VISIBLE);
				animationset.addAnimation(alphaAnimation);
				btAdd.startAnimation(animationset);
				animationset.addAnimation(alphaAnimation3);
				btDelete.startAnimation(animationset);
				animationset.addAnimation(alphaAnimation2);
				btLucky.startAnimation(animationset);
				}else{
					AnimationSet animationset1=new AnimationSet(true);
					TranslateAnimation alphaAnimationx=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
							0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
					TranslateAnimation alphaAnimationw=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
							0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 2f);
					TranslateAnimation alphaAnimationz=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
							0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 4f);
					alphaAnimationx.setDuration(300);
					animationset1.addAnimation(alphaAnimationx);
					//animationset1.setRepeatMode(Animation.REVERSE);
					//animationset1.setFillAfter(true);
					btAdd.startAnimation(animationset1);
					alphaAnimationw.setDuration(300);
					animationset1.addAnimation(alphaAnimationw);
					btDelete.startAnimation(animationset1);
					alphaAnimationz.setDuration(300);
					animationset1.addAnimation(alphaAnimationz);
					btLucky.startAnimation(animationset1);
					alphaAnimationx.setAnimationListener(new AnimationListener() {
						
						@Override
						public void onAnimationStart(Animation animation) {							
							
						}
						
						@Override
						public void onAnimationRepeat(Animation animation) {							
							
						}
						
						@Override
						public void onAnimationEnd(Animation animation) {							
							btAdd.clearAnimation();
							btDelete.clearAnimation();							
							btLucky.clearAnimation();							
							btAdd.setVisibility(View.GONE);
							btDelete.setVisibility(View.GONE);
							btLucky.setVisibility(View.GONE);
						}
					});
					
				}
				flag=!flag;
			}
		});

		/**
		 * ���ð�ť����ʹ�����Ի���
		 */
		btAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialogShow();
			}
		});
		/**
		 * ���ɾ����ť
		 */
		btDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gvAdapter.deleteToggle();
				gvAdapter.notifyDataSetChanged();				
			}
		});
		/**
		 * ���lucky��ť
		 */
		btLucky.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),RotateCardActivity.class);
				startActivity(intent);						
			}
		});
	}








	/**
	 * �����Ի��򷽷�
	 */
	protected void alertDialogShow() {
		// �����Ի��򹹽���
		View view2 = View.inflate(getActivity(), R.layout.alertdialog, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
		// ��ȡ����
		builder.setView(view2);

		// ��ȡ�����еĿؼ�
		final EditText edGid = (EditText) view2.findViewById(R.id.etAddGid);

		ImageButton button = (ImageButton) view2.findViewById(R.id.alertSubmit);

		// ���ò���
		// builder.setTitle("Login").setIcon(R.drawable.ic_launcher)
		//				button.setClickable(false);
		// �����Ի���

		alertDialog = builder.create();

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){
				Log.i("w", "onClick");
				Log.i("alert", "�Ի���ť����");

				final String gid = edGid.getText().toString().trim();
				stockModel.loadStockInfoByStockGid(gid, new StockListCallBack() {

					@Override
					public void OnStockListLoaded(List<Data> Stocks) {																								
						DBHelper.SaveGidInfo(gid);
						Log.i("alert", gid+"����ɹ�");
						//gvAdapter.notifyDataSetChanged();

					}
				}, GlobalData.UNNOMAL);



			}

		});

		edGid.addTextChangedListener(new TextWatcher() {

			private int charMaxNum=6;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {


			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				int a=charMaxNum-edGid.getText().length();
				if(a==0){

					Toast.makeText(getActivity(), "������ϣ���ȷ��", Toast.LENGTH_SHORT).show();							

				}else{

				}
			}
		});			
		alertDialog.show();							
	}



	/**
	 * ������app�����stocks��ֵ
	 */
	private void setStocks() {

		/**
		 * �ݹ����setStock����
		 */
		userInfos =DBHelper.GetUserList();
		
		Log.i("��ȡlist", "userinfos"+userInfos.size());
		if(a<userInfos.size()){	
			Log.i("w", "userInfos"+userInfos.size());
			stockModel.loadStockInfoByStockGid(userInfos.get(a).getGidcode(), new StockListCallBack() {
				@Override
				public void OnStockListLoaded(List<Data> stocks) {
					Log.i("��ֵstock", "app��stocks�Ѹ�ֵ"+stocks.size());
					Log.i("w", "a��ֵΪ"+a);
					a++;
					setStocks();
					gvAdapter=new GridViewAdapter(getActivity(),stocks,gridView);
					gridView.setAdapter(gvAdapter);
				}
			},GlobalData.NOMAL);

		}else{
			
			Log.i("w", "stocks�ĳ���"+stocks.size());
			//gvAdapter=new GridViewAdapter(getActivity(),stocks,gridView);
			Log.w("stocks����������", "�����񲼾�");
			//gridView.setAdapter(gvAdapter);
			llloading.setVisibility(View.GONE);
			btAdd.setEnabled(true);
			btSet.setEnabled(true);
		}


	}
	
	@Override
	public void onDestroyView() {
//		List<Data> stocks = StockApp.getApp().getStocks();
		stocks.clear();
//		gvAdapter.notifyDataSetChanged();
		Log.i("w", "destroy");
		super.onDestroyView();
	}

	

}


////for (int i = 0; i < userInfos.size(); i++) {
//////data=userInfos.get(i).getGIDCODE();
////a=i;
////Thread thread=new Thread(new Runnable() {
////public void run() {
////
////	stockModel.loadStockInfoByStockGid(userInfos.get(a).getGidcode(), new StockListCallBack(){
////
////		@Override
////		public void OnStockListLoaded(List<Data> Stocks) {
////			gvAdapter=new GridViewAdapter(getActivity(), Stocks);
////			gridView.setAdapter(gvAdapter);
////			
////		}
////		
////	});
////}
////});
////thread.start();
////}
////
//
////}
//
//
////
////private void newThread(final int i) {
////Thread thread=new Thread(){
////@Override
////public void run() {
////stockModel.loadStockInfoByStockGid(userInfos.get(i).getGIDCODE(), new StockListCallBack() {
////	int a=userInfos.size();
////	
////	@Override
////	public void OnStockListLoaded(List<Data> Stocks) {
//////		if(Stocks.size()==userInfos.size()){								
////			gvAdapter=new GridViewAdapter(getActivity(), Stocks);
////			gridView.setAdapter(gvAdapter);
//////		}
////		
////	}
////	
////});
////super.run();
////}
////};
////thread.start();
////
////}


//		mHandler=new Handler(){
//
//			@Override
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case OK:
//					a++;
//					break;				
//				}
//
//			}
//
//		};
//		
//			stockModel.loadStockInfoByStockGid(userInfos.get(a).getGIDCODE(), new StockListCallBack() {
//
//				@Override
//				public void OnStockListLoaded(List<Data> stocks) {
//					Log.i("��ֵstock", "app��stocks�Ѹ�ֵ"+stocks.size());
//					gvAdapter=new GridViewAdapter(getActivity(), stocks);
//					Log.w("stocks����������", "�����񲼾�");
//					gridView.setAdapter(gvAdapter);
//					mHandler.sendEmptyMessage(OK);
//					//a++;
//				}
//
//			});
//		
//		Thread thread=new Thread(){
//			@Override
//			public	synchronized void run() {
//				int a=0;
//				while(a<userInfos.size()){
//				synchronized ("lock"){
//					Log.i("lock", "userinfos"+userInfos.get(a).toString());
//					stockModel.loadStockInfoByStockGid(userInfos.get(a).getGIDCODE(), new StockListCallBack() {
//						
//						private static final int OK=1;
//
//						@Override
//						public void OnStockListLoaded(List<Data> stocks) {
//							Log.i("��ֵstock", "app��stocks�Ѹ�ֵ"+stocks.size());
//							gvAdapter=new GridViewAdapter(getActivity(), stocks);
//							Log.w("stocks����������", "�����񲼾�");
//							gridView.setAdapter(gvAdapter);
//							Handler handler=new Handler();
//							handler.sendEmptyMessage(OK);
//					}
//					});
//					a++;
//				}
//			}
//			}
//			};
//			thread.start();
//	}
//}

//		for (int i = 0; i < userInfos.size(); i++) {		
//			stockModel.loadStockInfoByStockGid(userInfos.get(i).getGIDCODE(), new StockListCallBack() {
//				@Override
//				public void OnStockListLoaded(List<Data> stocks) {
//					Log.i("��ֵstock", "app��stocks�Ѹ�ֵ"+stocks.size());
//					gvAdapter=new GridViewAdapter(getActivity(), stocks);
//					Log.w("stocks����������", "�����񲼾�");
//					gridView.setAdapter(gvAdapter);	
//				}
//			});
//
//		}
//		
//	}
//}
//	public class MyThread extends Thread {
//		public Object lock;
//				
//		
//		
//		public MyThread(Object obj) {
//			super();
//			this.lock = obj;
//		}
//
//
//		@Override
//		public synchronized void run() {			
//			synchronized (lock) {
//				
//			}
//		}
//	}

