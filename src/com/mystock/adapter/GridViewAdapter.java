package com.mystock.adapter;

import java.util.List;

import com.mystock.R;
import com.mystock.dao.DataHelper;
import com.mystock.dao.IDao;
import com.mystock.entity.Data;
import com.mystock.utils.MyListener;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private List<Data>stocks;
	private LayoutInflater inflater;
	private GridView gridView;
	private IDao dao;
	private int a;
	public GridViewAdapter(Context context, List<Data> stocks,GridView gridView) {
		a=0;
		this.context = context;
		this.stocks = stocks;
		this.gridView=gridView;
		Log.i("w", "传入adapter的stocks数组size为"+stocks.size());
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return stocks.size();
	}

	@Override
	public Data getItem(int position) {

		return stocks.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Log.w("adapter中的getview方法", " ");
		viewHolder holder=null;
		if(convertView==null){
			a++;
			convertView=inflater.inflate(R.layout.item_gv_stock, null);
			Log.i("viewHolder", "新建viewholder");
			holder=new viewHolder();
			holder.name=(TextView) convertView.findViewById(R.id.tvStockName);
			holder.nowPri=(TextView) convertView.findViewById(R.id.tvNowPri);
			holder.increase=(TextView) convertView.findViewById(R.id.tvIncrease);
			holder.increper=(TextView) convertView.findViewById(R.id.tvIncreper);
			holder.ivDel = (ImageView) convertView.findViewById(R.id.ivDel);
			convertView.setTag(holder);
		}else{
			holder=(viewHolder) convertView.getTag();
		}
		Data s=getItem(position);
		if(s.getIncrease().contains("-")){
			holder.increase.setTextColor(Color.parseColor("#00FF00"));
		}else if(Float.parseFloat(s.getIncrease())>0){
			holder.increase.setTextColor(Color.parseColor("#FF0000"));		
		}else{
			holder.increase.setTextColor(Color.parseColor("#000000"));					
		}
		if(s.getIncrePer().contains("-")){
			holder.increper.setTextColor(Color.parseColor("#00FF00"));
		}else if(Float.parseFloat(s.getIncrePer())>0){
			holder.increper.setTextColor(Color.parseColor("#FF0000"));		
		}else{
			holder.increper.setTextColor(Color.parseColor("#000000"));					
		}
		holder.ivDel.setOnClickListener(new MyListener(position) {

			@Override
			public void onClick(View v) {
				Log.i("w", "item"+getItem(position).getGid());
				//dao.DelUserInfo(getItem(position).getGid());
				DataHelper helper=new DataHelper(context);
				helper.DelUserInfo(getItem(position).getGid().substring(2));
				Log.i("deleteSQL", "item"+getItem(position).getGid());
				stocks.remove(position);
				GridViewAdapter.this.notifyDataSetChanged();
			}
		});
		holder.nowPri.setTextColor(holder.increase.getTextColors());
		holder.name.setText(s.getName());
		holder.nowPri.setText(s.getNowPri());	
		holder.increase.setText(s.getIncrease());
		holder.increper.setText(s.getIncrePer());
		holder.ivDel.setTag("ivDel" + position);

		if(!show) {		
			holder.ivDel.setVisibility(View.GONE);
//			holder.ivDel.setScaleX(0);
//			holder.ivDel.setScaleY(0);
		}else{
//			holder.ivDel.setScaleX(0);
//			holder.ivDel.setScaleY(0);
			holder.ivDel.setVisibility(View.VISIBLE);
//			setAnim();
//			holder.ivDel.setScaleX(1);
//			holder.ivDel.setScaleY(1);
		}

		//		holder.ivDel.setOnClickListener(new MyListener(position) {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				stocks.remove(position);
		//				Log.i("w", "item"+getItem(position).getGid());
		//				//dao.DelUserInfo(getItem(position).getGid());
		//				DataHelper helper=new DataHelper(context);
		//				helper.DelUserInfo(getItem(position).getGid());
		//				Log.i("deleteSQL", "item"+getItem(position).getGid());
		//				stocks.remove(position);
		//				GridViewAdapter.this.notifyDataSetChanged();
		//				
		//			}
		//		});
		return convertView;
	}


//	private void setAnim() {
//		final ImageView ivDel = (ImageView) gridView.findViewById(R.id.ivDel);
//		
//		ObjectAnimator anim = ObjectAnimator.ofFloat(ivDel, "abc", 0f, 1f);
//		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//			@Override
//			public void onAnimationUpdate(ValueAnimator valueAnimator) {
//				float val = (Float) valueAnimator.getAnimatedValue();
//				ivDel.setScaleX(val);
//				ivDel.setScaleY(val);
//			}
//		});
//		anim.setDuration(1500);
//		anim.start();
//		
//	}


	public boolean show = false;

	/**
	 * 切换显示删除图标
	 */

	public void deleteToggle() {
		
		if (show) { 
//
//			ObjectAnimator anim = ObjectAnimator.ofFloat(ivDel, "abc", 1f, 0f);
//			anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//				@Override
//				public void onAnimationUpdate(ValueAnimator valueAnimator) {//监听器
//					float val = (Float) valueAnimator.getAnimatedValue();
//					ivDel.setScaleX(val);
//					ivDel.setScaleY(val);
//				}
//			});
//			anim.setDuration(500);
//			anim.start();
			

			show = false;
		} else { //立即显示


//			ivDel.setVisibility(View.VISIBLE);
			

			show = true;
		}

	}

	class viewHolder{
		ImageView ivDel;
		TextView name,nowPri,increase,increper;

	}



}
