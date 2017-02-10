package com.mystock.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xutils.x;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

import com.mystock.R;
import com.mystock.app.StockApp;
import com.mystock.entity.Data;
import com.mystock.model.HistoryCallBack;
import com.mystock.model.HistoryInfoModel;
import com.mystock.model.StockListCallBack;
import com.mystock.model.StockModel;
import com.mystock.utils.GlobalData;

import android.R.integer;
import android.R.menu;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class StockActivity extends Activity implements OnTouchListener{

	

	private LineChartView lineChart;
	private List<PointValue> mPointValues = new ArrayList<PointValue>();
	private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
	private LinearLayout menuLayout;//菜单项
	private LinearLayout contentLayout;//内容项
	private LayoutParams  menuParams;//菜单项目的参数
	private LayoutParams contentParams;//内容项目的参数contentLayout的宽度值 

	private int disPlayWidth;//手机屏幕分辨率
	private float xDown;//手指点下去的横坐标
	private float xMove;//手指移动的横坐标
	private float xUp;//记录手指上抬后的横坐标

	private VelocityTracker mVelocityTracker; // 用于计算手指滑动的速度。  
	float velocityX;//手指左右移动的速度
	public static final int SNAP_VELOCITY = 400; //滚动显示和隐藏menu时，手指滑动需要达到的速度。 

	private boolean menuIsShow = false;//初始化菜单项不可翙
	private static final int menuPadding=80;//menu完成显示，留给content的宽度
	private HistoryInfoModel historyModel;
	private TextView tvName;
	private TextView tvGid;
	private TextView tvNowPri;
	private TextView tvIncrease;
	private TextView tvIncrePer;
	private ImageView ivPic;
	private RadioGroup rg;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private WebView webView;
	private StockModel stockModel;
	private ImageOptions options;
	private RadioButton crb1;
	private RadioButton crb2;
	private RadioButton crb3;
	private RadioButton crb4;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_stock);
		historyModel=new HistoryInfoModel();
		setLineCart();
		initLayoutParams();		
		Intent intent=getIntent();
		int position=intent.getIntExtra("int", 3);
		setView(position);
		setOnClick();
		
	}


	private void setLineCart() {
		int a=(int)(Math.random()*10-5);
		String[]bs={"89","91","92","96","97","98"};
		String b=bs[a];
		String url3="http://api.finance.ifeng.com/akdaily/?code=sh6019"+b+"&type=last";
		
		
		historyModel.parseUrl(url3, new HistoryCallBack() {
			
			
			
			@Override
			public void OnDateListLoaded(ArrayList<String> dates) {
				Log.i("dates", dates.toString());
				for (int j = 0; j < dates.size(); j++) {    
					mAxisXValues.add(new AxisValue(j).setLabel(dates.get(j)));					
					}
					
				}  
			
			@Override
			public void OnPriceListLoaded(ArrayList<Double> prices) {
				
				Log.i("prices", prices.toString());
				for (int i = 0; i < prices.size(); i++) {    
					mPointValues.add(new PointValue(i, Float.parseFloat(prices.get(i).toString())));				
				}    
				initLineChart();
			}
				
		});
	
			
		
		
		

	}


	/**
	 * 设置按钮监听
	 */

	private void setOnClick() {
		crb2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				toast();			
			}
		});
		crb3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				toast();			
			}
		});
		crb4.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				toast();			
			}
		});
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Log.i("checkId", checkedId+"");
				switch (checkedId) {
				case R.id.rb_1:
					rb1.setTextColor(Color.parseColor("#FF0000"));
					rb2.setTextColor(Color.parseColor("#888888"));
					rb3.setTextColor(Color.parseColor("#888888"));
					rb4.setTextColor(Color.parseColor("#888888"));
					x.image().bind(ivPic, GlobalData.urls.get(0), options);
					break;
				case R.id.rb_2:
					rb1.setTextColor(Color.parseColor("#888888"));
					rb2.setTextColor(Color.parseColor("#FF0000"));
					rb3.setTextColor(Color.parseColor("#888888"));
					rb4.setTextColor(Color.parseColor("#888888"));
					x.image().bind(ivPic, GlobalData.urls.get(1), options);
					break;
				case R.id.rb_3:
					rb1.setTextColor(Color.parseColor("#888888"));
					rb2.setTextColor(Color.parseColor("#888888"));
					rb3.setTextColor(Color.parseColor("#FF0000"));
					rb4.setTextColor(Color.parseColor("#888888"));
					x.image().bind(ivPic, GlobalData.urls.get(2), options);
					break;
				case R.id.rb_4:
					rb1.setTextColor(Color.parseColor("#888888"));
					rb2.setTextColor(Color.parseColor("#888888"));
					rb3.setTextColor(Color.parseColor("#888888"));
					rb4.setTextColor(Color.parseColor("#FF0000"));
					x.image().bind(ivPic, GlobalData.urls.get(3), options);

					break;
				}

			}
		});

	}


	protected void toast() {
		Toast.makeText(this, "后续开发中...",Toast.LENGTH_SHORT ).show();
		
	}


	/**
	 * 网页控件赋值
	 * @param position
	 */

	@SuppressLint("SetJavaScriptEnabled")
	private void setView(int position) {

		Data data=StockApp.getApp().getStocks().get(position);
		Log.i("w", "传过来的额position的值是"+position);
		tvName.setText(data.getName());
		tvGid.setText(data.getGid().substring(2));
		tvNowPri.setText(data.getNowPri());
		tvIncrease.setText(data.getIncrease());
		tvIncrePer.setText(data.getIncrePer());
		if(data.getIncrease().contains("-")){
			tvName.setTextColor(Color.parseColor("#00FF00"));
			tvGid.setTextColor(Color.parseColor("#00FF00"));
			tvNowPri.setTextColor(Color.parseColor("#00FF00"));
			tvIncrease.setTextColor(Color.parseColor("#00FF00"));
			tvIncrePer.setTextColor(Color.parseColor("#00FF00"));
		}else{
			tvName.setTextColor(Color.parseColor("#FF0000"));
			tvGid.setTextColor(Color.parseColor("#FF0000"));
			tvNowPri.setTextColor(Color.parseColor("#FF0000"));
			tvIncrease.setTextColor(Color.parseColor("#FF0000"));
			tvIncrePer.setTextColor(Color.parseColor("#FF0000"));			
		}   





		WebSettings webSettings = webView.getSettings();  
		webSettings.setJavaScriptEnabled(true);  



		// User settings          

		webSettings.setJavaScriptEnabled(true);  
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);  
		webSettings.setUseWideViewPort(true);//关键点  
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
		webSettings.setDisplayZoomControls(false);  
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本  
		webSettings.setAllowFileAccess(true); // 允许访问文件  
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮  
		webSettings.setSupportZoom(true); // 支持缩放  
		webSettings.setLoadWithOverviewMode(true);
		DisplayMetrics metrics = new DisplayMetrics();  
		getWindowManager().getDefaultDisplay().getMetrics(metrics);  
		int mDensity = metrics.densityDpi;  
		Log.d("maomao", "densityDpi = " + mDensity);  
		if (mDensity == 240) {   
			webSettings.setDefaultZoom(ZoomDensity.FAR);  
		} else if (mDensity == 160) {  
			webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
		} else if(mDensity == 120) {  
			webSettings.setDefaultZoom(ZoomDensity.CLOSE);  
		}else if(mDensity == DisplayMetrics.DENSITY_XHIGH){  
			webSettings.setDefaultZoom(ZoomDensity.FAR);   
		}else if (mDensity == DisplayMetrics.DENSITY_TV){  
			webSettings.setDefaultZoom(ZoomDensity.FAR);   
		}else{  
			webSettings.setDefaultZoom(ZoomDensity.MEDIUM);  
		}  

		webView.loadUrl("http://wap.sogou.com/news/newsSearchResult.jsp?mode=1&uID=s2-oRzd7vTWBoq_v&v=2&w=1180&p=1&keyword="+data.getGid().substring(2)+"&s=%E6%90%9C%E6%96%B0%E9%97%BB");
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);											

				return true;
			}

		});
		webView.setOnTouchListener(new OnTouchListener() {
			int startX=0;
			int scrollSize=100;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = (int) event.getX();
					break;
				case MotionEvent.ACTION_UP:
					int endX = (int) event.getX();
					if(endX>startX && webView.canGoBack() && endX-startX>scrollSize){
						webView.goBack();
					}else if(endX<startX &&webView.canGoForward() && startX-endX>scrollSize){
						webView.goForward();
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
		stockModel=new StockModel(null);
		stockModel.loadStockInfoByStockGid(data.getGid().substring(2),
				new StockListCallBack() {

			@Override
			public void OnStockListLoaded(List<Data> Stocks) {	
				
				options= new ImageOptions.Builder()
						//.setImageScaleType(ScaleType.FIT_XY)
						.setFadeIn(true) //淡入效果
						//ImageOptions.Builder()的一些其他属性：
						//.setCircular(true) //设置图片显示为圆形
						//.setSquare(true) //设置图片显示为正方形
						//.setCrop(true)
						//.setSize(DensityUtil.dip2px(500),DensityUtil.dip2px(500))
						//.setSize(width,200) //设置大小
						//.setAnimation(animation) //设置动画
						//.setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
						.setFailureDrawableId(R.drawable.pic_downloading) //以资源id设置加载失败的动画
						//.setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
						.setLoadingDrawableId(R.drawable.pic_downloading) //以资源id设置加载中的动画
						.setIgnoreGif(false) //忽略Gif图片
						//.setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
						//.setRadius(15) //设置拐角弧度
						.setUseMemCache(true) //设置使用MemCache，默认true
						.build();
				x.image().bind(ivPic, GlobalData.urls.get(0), options);
			}
		}, GlobalData.REQUESTPICS);

	}
	/**
	 *初始化Layout并设置其相应的参数
	 */
	private void initLayoutParams()
	{
		crb1=(RadioButton) findViewById(R.id.Crb_1);
		crb2=(RadioButton) findViewById(R.id.Crb_2);
		crb3=(RadioButton) findViewById(R.id.Crb_3);
		crb4=(RadioButton) findViewById(R.id.Crb_4);

		lineChart = (LineChartView)findViewById(R.id.line_chart);

		//		getAxisXLables();//获取x轴的标注
		//		getAxisPoints();//获取坐标点
		

		//得到屏幕的大小 
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);  
		disPlayWidth =dm.widthPixels;  

		//获得控件
		menuLayout = (LinearLayout) findViewById(R.id.menu);
		contentLayout = (LinearLayout)findViewById(R.id.content);
		findViewById(R.id.layout).setOnTouchListener(this);

		//获得控件参数
		menuParams=(LinearLayout.LayoutParams)menuLayout.getLayoutParams();
		contentParams = (LinearLayout.LayoutParams)contentLayout.getLayoutParams();

		//初始化菜单和内容的宽和边距
		menuParams.width = disPlayWidth - menuPadding;
		menuParams.leftMargin = 0 - menuParams.width;
		contentParams.width = disPlayWidth;
		contentParams.leftMargin=0;

		//设置参数
		menuLayout.setLayoutParams(menuParams);
		contentLayout.setLayoutParams(contentParams);
		tvName=(TextView) findViewById(R.id.detil_stockName);
		tvGid=(TextView) findViewById(R.id.detil_stockGrid);
		tvNowPri=(TextView) findViewById(R.id.detil_nowPri);
		tvIncrease=(TextView) findViewById(R.id.detil_increase);
		tvIncrePer=(TextView) findViewById(R.id.detil_increPer);
		ivPic=(ImageView) findViewById(R.id.detil_iv_detilpic1);
		rg=(RadioGroup) findViewById(R.id.detil_rg);
		rb1=(RadioButton) findViewById(R.id.rb_1);
		rb2=(RadioButton) findViewById(R.id.rb_2);
		rb3=(RadioButton) findViewById(R.id.rb_3);
		rb4=(RadioButton) findViewById(R.id.rb_4);
		webView=(WebView) findViewById(R.id.detill_Webview);

	}


	/**
	 * 初始化Linecart
	 * 
	 */
	private void initLineChart() {
		Line line = new Line(mPointValues).setColor(Color.parseColor("#FF0000"));  //折线的颜色
		List<Line> lines = new ArrayList<Line>();    
		line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
		line.setCubic(false);//曲线是否平滑
		line.setStrokeWidth(1);//线条的粗细，默认是3
		line.setFilled(true);//是否填充曲线的面积
		line.setHasLabels(false);//曲线的数据坐标是否加上备注
		//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
		line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示	
		line.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示	
		lines.add(line);  
		LineChartData data = new LineChartData();  
		data.setLines(lines);  

		//坐标轴  
		Axis axisX = new Axis(); //X轴  
		axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示 
		//	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
		axisX.setTextColor(Color.parseColor("#D6D6D9"));//灰色

		//	    axisX.setName("未来几天的天气");  //表格名称
		axisX.setTextSize(11);//设置字体大小
		axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
		axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
		data.setAxisXBottom(axisX); //x 轴在底部     
		//	    data.setAxisXTop(axisX);  //x 轴在顶部
		axisX.setHasLines(false); //x 轴分割线


		Axis axisY = new Axis();  //Y轴  
		axisY.setName("price");//y轴标注
		axisY.setTextSize(11);//设置字体大小
		data.setAxisYLeft(axisY);  //Y轴设置在左边
		//data.setAxisYRight(axisY);  //y轴设置在右边
		//设置行为属性，支持缩放、滑动以及平移  
		lineChart.setInteractive(true); 
		lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
		lineChart.setMaxZoom((float) 150);//缩放比例
		lineChart.setLineChartData(data);  
		lineChart.setVisibility(View.VISIBLE);
		/**注：下面的7，10只是代表一个数字去类比而已
		 * 尼玛搞的老子好辛苦！！！见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
		 * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
		 * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
		 * 若设置axisX.setMaxLabelChars(int count)这句话,
		 * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
	                     刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
	  	         若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
		 * 并且Y轴是根据数据的大小自动设置Y轴上限
		 * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
		 */
//		Viewport v = new Viewport(lineChart.getMaximumViewport()); 
//		v.left = 7; 
//		v.right= 0; 
//		lineChart.setCurrentViewport(v);
	}



	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		acquireVelocityTracker(event);
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			xDown=event.getRawX(); 	  
			break;

		case MotionEvent.ACTION_MOVE:
			xMove=event.getRawX();   
			isScrollToShowMenu();
			break;

		case MotionEvent.ACTION_UP:
			xUp=event.getRawX();       
			isShowMenu();
			releaseVelocityTracker();  
			break;

		case MotionEvent.ACTION_CANCEL:  
			releaseVelocityTracker();  
			break;  
		}
		return true;
	}
	/**
	 * 根据手指按下的距离，判断是否滚动显示菜单
	 */
	private void isScrollToShowMenu()
	{
		int distanceX = (int) (xMove - xDown);      
		if (!menuIsShow) {
			scrollToShowMenu(distanceX);
		}else{
			scrollToHideMenu(distanceX);
		}
	}
	/**
	 * 手指抬起之后判断是否要显示菜单
	 */
	private void isShowMenu()
	{
		velocityX =getScrollVelocity();
		if(wantToShowMenu()){
			if(shouldShowMenu()){
				showMenu();
			}else{
				hideMenu();
			}
		}
		else if(wantToHideMenu()){
			if(shouldHideMenu()){
				hideMenu();
			}else{
				showMenu();
			}
		}    
	}
	/**
	 *想要显示菜单,当向右移动距离大于0并且菜单不可见
	 */
	private boolean wantToShowMenu(){
		return !menuIsShow&&xUp-xDown>0;
	}
	/**
	 *想要隐藏菜单,当向左移动距离大于0并且菜单可见
	 */
	private boolean wantToHideMenu(){
		return menuIsShow&&xDown-xUp>0;
	}
	/**
	 *判断应该显示菜单,当向右移动的距离超过菜单的一半或者速度超过给定值
	 */
	private boolean shouldShowMenu(){
		return xUp-xDown>menuParams.width/2||velocityX>SNAP_VELOCITY;
	}
	/**
	 *判断应该隐藏菜单,当向左移动的距离超过菜单的一半或者速度超过给定值
	 */
	private boolean shouldHideMenu(){
		return xDown-xUp>menuParams.width/2||velocityX>SNAP_VELOCITY;
	}
	/**
	 * 显示菜单栏
	 */
	private void showMenu()
	{
		new showMenuAsyncTask().execute(50);
		menuIsShow=true;
	}
	/**
	 * 隐藏菜单栏
	 */
	private void hideMenu()
	{
		new showMenuAsyncTask().execute(-50);
		menuIsShow=false;
	}
	/**
	 *指针按着时，滚动将菜单慢慢显示出来
	 *@param scrollX 每次滚动移动的距离
	 */
	private void scrollToShowMenu(int scrollX)
	{
		if(scrollX>0&&scrollX<= menuParams.width)
			menuParams.leftMargin =-menuParams.width+scrollX;
		menuLayout.setLayoutParams(menuParams); 
	}
	/**
	 *指针按着时，滚动将菜单慢慢隐藏出来
	 *@param scrollX 每次滚动移动的距离
	 */
	private void scrollToHideMenu(int scrollX)
	{
		if(scrollX>=-menuParams.width&&scrollX<0)
			menuParams.leftMargin=scrollX;
		menuLayout.setLayoutParams(menuParams); 
	}


	/**  
	 * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。 
	 * @param event 向VelocityTracker添加MotionEvent  
	 */  
	private void acquireVelocityTracker(final MotionEvent event) {  
		if(null == mVelocityTracker) {  
			mVelocityTracker = VelocityTracker.obtain();  
		}  
		mVelocityTracker.addMovement(event);  
	}  
	/** 
	 * 获取手指在content界面滑动的速度。 
	 * @return 滑动速度，以每秒钟移动了多少像素值为单位。 
	 */  
	private int getScrollVelocity() {  
		mVelocityTracker.computeCurrentVelocity(1000);  
		int velocity = (int) mVelocityTracker.getXVelocity();  

		return Math.abs(velocity);  
	} 
	/**  
	 * 释放VelocityTracker  
	 */  
	private void releaseVelocityTracker() {  
		if(null != mVelocityTracker) {  
			mVelocityTracker.clear();  
			mVelocityTracker.recycle();  
			mVelocityTracker = null;  
		}  
	}  
	/**
	 *
	 *：模拟动画过程，让肉眼能看到滚动的效果
	 *
	 */
	class showMenuAsyncTask extends AsyncTask<Integer, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(Integer... params)
		{
			int leftMargin = menuParams.leftMargin;
			while (true)
			{// 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。
				leftMargin += params[0];
				if (params[0] > 0 && leftMargin > 0)
				{
					leftMargin= 0;
					break;
				} else if (params[0] < 0 && leftMargin <-menuParams.width)
				{
					leftMargin=-menuParams.width;
					break;
				}
				publishProgress(leftMargin);
				try
				{
					Thread.sleep(40);//休眠一下，肉眼才能看到滚动效果
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			return leftMargin;
		}
		@Override
		protected void onProgressUpdate(Integer... value)
		{
			menuParams.leftMargin = value[0];
			menuLayout.setLayoutParams(menuParams);
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			menuParams.leftMargin = result;
			menuLayout.setLayoutParams(menuParams);
		}

	}
}
