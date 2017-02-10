package com.mystock.fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mystock.R;
import com.mystock.entity.Data2;
import com.mystock.model.AllStockModel;
import com.mystock.model.ListData2CallBack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Fragment2 extends Fragment{
	private ListView listview;
	private AllStockModel model;
	private static int a=1;
	private static ArrayList<Data2> data2=new ArrayList<Data2>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment2, null);
		initView(view);
		setListView(a);
		setOnTOuchListener();
		return view;
	}

	private void setOnTOuchListener() {
		listview.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Adapter adapter=listview.getAdapter();
				if(!adapter.isEmpty()){
				  final int lastItemPosition = adapter.getCount() - 1;			
			        final int lastVisiblePosition = listview.getLastVisiblePosition();
			        if(lastItemPosition==lastVisiblePosition){
			        	setListView(++a);
			        	
			        }
				}else{
					Toast.makeText(getActivity(), "数据加载中...", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});
		
	}

	private void setListView(int b) {
	

			
				model=new AllStockModel();
				model.getRequest(b,new ListData2CallBack() {

					@Override
					public void OnData2ListLoaded(ArrayList<Data2> data2s) {
						listview.setAdapter(new SimpleAdapter(getActivity(), 
								getData(data2s),   
								android.R.layout.simple_list_item_2,   
								new String[]{"title", "description"},   
								new int[]{android.R.id.text1, android.R.id.text2}));
						listview.setSelection((a-1)*10);
					}
				});

			}



	

	private void initView( View view) {
		listview=(ListView) view.findViewById(R.id.LvMyStock);

	}



	private List<Map<String, String>> getData(ArrayList<Data2> data2s) {  
		List<Map<String, String>> listData = new ArrayList<Map<String, String>>();  
		data2.addAll(data2s);
		for (int j = 0; j < data2.size(); j++) {
			Map<String, String> map = new HashMap<String, String>();  
			map.put("title", data2.get(j).getName());  
			map.put("description", "买"+data2.get(j).getBuy()+"  "+"卖"+data2.get(j).getSell());  
			listData.add(map);  		
		}        
		return listData;  
	}  


}
