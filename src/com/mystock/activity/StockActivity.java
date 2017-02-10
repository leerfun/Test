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
	private LinearLayout menuLayout;//�˵���
	private LinearLayout contentLayout;//������
	private LayoutParams  menuParams;//�˵���Ŀ�Ĳ���
	private LayoutParams contentParams;//������Ŀ�Ĳ���contentLayout�Ŀ��ֵ 

	private int disPlayWidth;//�ֻ���Ļ�ֱ���
	private float xDown;//��ָ����ȥ�ĺ�����
	private float xMove;//��ָ�ƶ��ĺ�����
	private float xUp;//��¼��ָ��̧��ĺ�����

	private VelocityTracker mVelocityTracker; // ���ڼ�����ָ�������ٶȡ�  
	float velocityX;//��ָ�����ƶ����ٶ�
	public static final int SNAP_VELOCITY = 400; //������ʾ������menuʱ����ָ������Ҫ�ﵽ���ٶȡ� 

	private boolean menuIsShow = false;//��ʼ���˵������
	private static final int menuPadding=80;//menu�����ʾ������content�Ŀ��
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
	 * ���ð�ť����
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
		Toast.makeText(this, "����������...",Toast.LENGTH_SHORT ).show();
		
	}


	/**
	 * ��ҳ�ؼ���ֵ
	 * @param position
	 */

	@SuppressLint("SetJavaScriptEnabled")
	private void setView(int position) {

		Data data=StockApp.getApp().getStocks().get(position);
		Log.i("w", "�������Ķ�position��ֵ��"+position);
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
		webSettings.setUseWideViewPort(true);//�ؼ���  
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
		webSettings.setDisplayZoomControls(false);  
		webSettings.setJavaScriptEnabled(true); // ����֧��javascript�ű�  
		webSettings.setAllowFileAccess(true); // ��������ļ�  
		webSettings.setBuiltInZoomControls(true); // ������ʾ���Ű�ť  
		webSettings.setSupportZoom(true); // ֧������  
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
						.setFadeIn(true) //����Ч��
						//ImageOptions.Builder()��һЩ�������ԣ�
						//.setCircular(true) //����ͼƬ��ʾΪԲ��
						//.setSquare(true) //����ͼƬ��ʾΪ������
						//.setCrop(true)
						//.setSize(DensityUtil.dip2px(500),DensityUtil.dip2px(500))
						//.setSize(width,200) //���ô�С
						//.setAnimation(animation) //���ö���
						//.setFailureDrawable(Drawable failureDrawable) //���ü���ʧ�ܵĶ���
						.setFailureDrawableId(R.drawable.pic_downloading) //����Դid���ü���ʧ�ܵĶ���
						//.setLoadingDrawable(Drawable loadingDrawable) //���ü����еĶ���
						.setLoadingDrawableId(R.drawable.pic_downloading) //����Դid���ü����еĶ���
						.setIgnoreGif(false) //����GifͼƬ
						//.setParamsBuilder(ParamsBuilder paramsBuilder) //���������������һЩ����
						//.setRadius(15) //���ùսǻ���
						.setUseMemCache(true) //����ʹ��MemCache��Ĭ��true
						.build();
				x.image().bind(ivPic, GlobalData.urls.get(0), options);
			}
		}, GlobalData.REQUESTPICS);

	}
	/**
	 *��ʼ��Layout����������Ӧ�Ĳ���
	 */
	private void initLayoutParams()
	{
		crb1=(RadioButton) findViewById(R.id.Crb_1);
		crb2=(RadioButton) findViewById(R.id.Crb_2);
		crb3=(RadioButton) findViewById(R.id.Crb_3);
		crb4=(RadioButton) findViewById(R.id.Crb_4);

		lineChart = (LineChartView)findViewById(R.id.line_chart);

		//		getAxisXLables();//��ȡx��ı�ע
		//		getAxisPoints();//��ȡ�����
		

		//�õ���Ļ�Ĵ�С 
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);  
		disPlayWidth =dm.widthPixels;  

		//��ÿؼ�
		menuLayout = (LinearLayout) findViewById(R.id.menu);
		contentLayout = (LinearLayout)findViewById(R.id.content);
		findViewById(R.id.layout).setOnTouchListener(this);

		//��ÿؼ�����
		menuParams=(LinearLayout.LayoutParams)menuLayout.getLayoutParams();
		contentParams = (LinearLayout.LayoutParams)contentLayout.getLayoutParams();

		//��ʼ���˵������ݵĿ�ͱ߾�
		menuParams.width = disPlayWidth - menuPadding;
		menuParams.leftMargin = 0 - menuParams.width;
		contentParams.width = disPlayWidth;
		contentParams.leftMargin=0;

		//���ò���
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
	 * ��ʼ��Linecart
	 * 
	 */
	private void initLineChart() {
		Line line = new Line(mPointValues).setColor(Color.parseColor("#FF0000"));  //���ߵ���ɫ
		List<Line> lines = new ArrayList<Line>();    
		line.setShape(ValueShape.CIRCLE);//����ͼ��ÿ�����ݵ����״  ������Բ�� �������� ��ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE��
		line.setCubic(false);//�����Ƿ�ƽ��
		line.setStrokeWidth(1);//�����Ĵ�ϸ��Ĭ����3
		line.setFilled(true);//�Ƿ�������ߵ����
		line.setHasLabels(false);//���ߵ����������Ƿ���ϱ�ע
		//		line.setHasLabelsOnlyForSelected(true);//�������������ʾ���ݣ����������line.setHasLabels(true);����Ч��
		line.setHasLines(true);//�Ƿ���ֱ����ʾ�����Ϊfalse ��û������ֻ�е���ʾ	
		line.setHasPoints(false);//�Ƿ���ʾԲ�� ���Ϊfalse ��û��ԭ��ֻ�е���ʾ	
		lines.add(line);  
		LineChartData data = new LineChartData();  
		data.setLines(lines);  

		//������  
		Axis axisX = new Axis(); //X��  
		axisX.setHasTiltedLabels(true);  //X������������������б����ʾ����ֱ�ģ�true��б����ʾ 
		//	    axisX.setTextColor(Color.WHITE);  //����������ɫ
		axisX.setTextColor(Color.parseColor("#D6D6D9"));//��ɫ

		//	    axisX.setName("δ�����������");  //�������
		axisX.setTextSize(11);//���������С
		axisX.setMaxLabelChars(7); //��༸��X�����꣬��˼�������������X�������ݵĸ���7<=x<=mAxisValues.length
		axisX.setValues(mAxisXValues);  //���X�����������
		data.setAxisXBottom(axisX); //x ���ڵײ�     
		//	    data.setAxisXTop(axisX);  //x ���ڶ���
		axisX.setHasLines(false); //x ��ָ���


		Axis axisY = new Axis();  //Y��  
		axisY.setName("price");//y���ע
		axisY.setTextSize(11);//���������С
		data.setAxisYLeft(axisY);  //Y�����������
		//data.setAxisYRight(axisY);  //y���������ұ�
		//������Ϊ���ԣ�֧�����š������Լ�ƽ��  
		lineChart.setInteractive(true); 
		lineChart.setZoomType(ZoomType.HORIZONTAL);  //�������ͣ�ˮƽ
		lineChart.setMaxZoom((float) 150);//���ű���
		lineChart.setLineChartData(data);  
		lineChart.setVisibility(View.VISIBLE);
		/**ע�������7��10ֻ�Ǵ���һ������ȥ��ȶ���
		 * ���������Ӻ����࣡��������http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2��;
		 * ���漸���������X�����ݵ���ʾ������x��0-7�����ݣ��������ݵ����С�ڣ�29����ʱ����С������hellochartĬ�ϵ���������ʾ�������ݵ�������ڣ�29����ʱ��
		 * ��������axisX.setMaxLabelChars(int count)��仰,����Զ�����X��������ʾ�ľ������ʵ����ݸ�����
		 * ������axisX.setMaxLabelChars(int count)��仰,
		 * 33�����ݵ���ԣ��� axisX.setMaxLabelChars(10);�����10����v.right= 7; �����7����
	                     �տ�ʼX����ʾ7�����ݣ�Ȼ�����ŵ�ʱ��X��ĸ����ᱣ֤����7С��10
	  	         ��С��v.right= 7;�е�7,�����Ҹо��������䶼����ʧЧ�˵����� - -!
		 * ����Y���Ǹ������ݵĴ�С�Զ�����Y������
		 * ����������� v.right= 7; ��仰����ͼ��տ�ʼ�ͻᾡ���ܵ���ʾ�������ݣ�������̫��
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
	 * ������ָ���µľ��룬�ж��Ƿ������ʾ�˵�
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
	 * ��ָ̧��֮���ж��Ƿ�Ҫ��ʾ�˵�
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
	 *��Ҫ��ʾ�˵�,�������ƶ��������0���Ҳ˵����ɼ�
	 */
	private boolean wantToShowMenu(){
		return !menuIsShow&&xUp-xDown>0;
	}
	/**
	 *��Ҫ���ز˵�,�������ƶ��������0���Ҳ˵��ɼ�
	 */
	private boolean wantToHideMenu(){
		return menuIsShow&&xDown-xUp>0;
	}
	/**
	 *�ж�Ӧ����ʾ�˵�,�������ƶ��ľ��볬���˵���һ������ٶȳ�������ֵ
	 */
	private boolean shouldShowMenu(){
		return xUp-xDown>menuParams.width/2||velocityX>SNAP_VELOCITY;
	}
	/**
	 *�ж�Ӧ�����ز˵�,�������ƶ��ľ��볬���˵���һ������ٶȳ�������ֵ
	 */
	private boolean shouldHideMenu(){
		return xDown-xUp>menuParams.width/2||velocityX>SNAP_VELOCITY;
	}
	/**
	 * ��ʾ�˵���
	 */
	private void showMenu()
	{
		new showMenuAsyncTask().execute(50);
		menuIsShow=true;
	}
	/**
	 * ���ز˵���
	 */
	private void hideMenu()
	{
		new showMenuAsyncTask().execute(-50);
		menuIsShow=false;
	}
	/**
	 *ָ�밴��ʱ���������˵�������ʾ����
	 *@param scrollX ÿ�ι����ƶ��ľ���
	 */
	private void scrollToShowMenu(int scrollX)
	{
		if(scrollX>0&&scrollX<= menuParams.width)
			menuParams.leftMargin =-menuParams.width+scrollX;
		menuLayout.setLayoutParams(menuParams); 
	}
	/**
	 *ָ�밴��ʱ���������˵��������س���
	 *@param scrollX ÿ�ι����ƶ��ľ���
	 */
	private void scrollToHideMenu(int scrollX)
	{
		if(scrollX>=-menuParams.width&&scrollX<0)
			menuParams.leftMargin=scrollX;
		menuLayout.setLayoutParams(menuParams); 
	}


	/**  
	 * ����VelocityTracker���󣬲�������content����Ļ����¼����뵽VelocityTracker���С� 
	 * @param event ��VelocityTracker���MotionEvent  
	 */  
	private void acquireVelocityTracker(final MotionEvent event) {  
		if(null == mVelocityTracker) {  
			mVelocityTracker = VelocityTracker.obtain();  
		}  
		mVelocityTracker.addMovement(event);  
	}  
	/** 
	 * ��ȡ��ָ��content���滬�����ٶȡ� 
	 * @return �����ٶȣ���ÿ�����ƶ��˶�������ֵΪ��λ�� 
	 */  
	private int getScrollVelocity() {  
		mVelocityTracker.computeCurrentVelocity(1000);  
		int velocity = (int) mVelocityTracker.getXVelocity();  

		return Math.abs(velocity);  
	} 
	/**  
	 * �ͷ�VelocityTracker  
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
	 *��ģ�⶯�����̣��������ܿ���������Ч��
	 *
	 */
	class showMenuAsyncTask extends AsyncTask<Integer, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(Integer... params)
		{
			int leftMargin = menuParams.leftMargin;
			while (true)
			{// ���ݴ�����ٶ����������棬������������߽���ұ߽�ʱ������ѭ����
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
					Thread.sleep(40);//����һ�£����۲��ܿ�������Ч��
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
