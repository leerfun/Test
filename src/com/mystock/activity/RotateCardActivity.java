package com.mystock.activity;
import java.util.Random;

import com.mystock.R;
import com.mystock.entity.CustomTextView;
import com.mystock.entity.Rotatable;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
public class RotateCardActivity extends Activity implements OnClickListener {

	private static final String LOG_TAG = "RotateCardActivity";
	private CustomTextView alert;
	private RelativeLayout rlCardRoot;
	private ImageView imageViewBack;
	private ImageView imageViewFront;


	private void initView() {
		rlCardRoot = (RelativeLayout) findViewById(R.id.rl_card_root);
		imageViewBack = (ImageView) findViewById(R.id.imageView_back);
		imageViewFront = (ImageView) findViewById(R.id.imageView_front);
		alert=(CustomTextView) findViewById(R.id.alert);
		imageViewBack.setOnClickListener(this);
		imageViewFront.setOnClickListener(this);
		setCameraDistance();

	}




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotate_card);
		initView();
		initText();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
				.Builder(this)  
				//.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
				//.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
				.threadPoolSize(3)//线程池内加载的数量  
				.threadPriority(Thread.NORM_PRIORITY - 2)  
				.denyCacheImageMultipleSizesInMemory()  
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
				.memoryCacheSize(2 * 1024 * 1024)    
				.discCacheSize(50 * 1024 * 1024)    
				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
				.tasksProcessingOrder(QueueProcessingType.LIFO)  
				.discCacheFileCount(100) //缓存的文件数量  
				//.discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径  
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
				.imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
				// .writeDebugLogs() // Remove for release app  
				.build();//开始构建  
		// Initialize ImageLoader with configuration
		ImageLoader.getInstance().init(config);
		initData();
	}

	private void initText() {
		int x=(int)(Math.random()*100+200);
		alert.setText("推荐600"+x+"");
		
	}




	/**
	 * 设置数据
	 */
	public void initData() {
		String imageUri = "drawable://" + R.drawable.blue_back;
		ImageLoader.getInstance().displayImage(imageUri, imageViewBack);
		//, DisplayImageOptionsUtil.getDisplayImageOptionsNoCache(R.drawable.card_bg_gray));
		imageUri = "drawable://" + R.drawable.blue_front;
		ImageLoader.getInstance().displayImage(imageUri, imageViewFront);
		//DisplayImageOptionsUtil.getDisplayImageOptionsNoCache(R.drawable.card_bg_gray));
		imageViewBack.setVisibility(View.VISIBLE);
		imageViewFront.setVisibility(View.INVISIBLE);
	}


	/**
	 * 翻牌
	 */
	public void cardTurnover(int a) {
		if (View.VISIBLE == imageViewBack.getVisibility()) {
			ViewHelper.setRotationY(imageViewFront, 180f);//先翻转180，转回来时就不是反转的了
			Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
					.sides(R.id.imageView_back, R.id.imageView_front)
					.direction(Rotatable.ROTATE_Y)
					.rotationCount(1)
					.build();
			rotatable.setTouchEnable(false);
			rotatable.rotate(Rotatable.ROTATE_Y, -180, 1500,new AnimatorListenerAdapter(){
				@Override
				public void onAnimationEnd(Animator animation) {
					alert.setVisibility(View.VISIBLE);
					super.onAnimationEnd(animation);
				}
			});
		} else if (View.VISIBLE == imageViewFront.getVisibility()) {
			Rotatable rotatable = new Rotatable.Builder(rlCardRoot)
					.sides(R.id.imageView_back, R.id.imageView_front)
					.direction(Rotatable.ROTATE_Y)
					.rotationCount(1)
					.build();
			rotatable.setTouchEnable(false);	           
			rotatable.rotate(Rotatable.ROTATE_Y, 0, 1500);
		}
	}


	/**
	 * 改变视角距离, 贴近屏幕
	 */
	private void setCameraDistance() {
		int distance = 10000;
		float scale = getResources().getDisplayMetrics().density * distance;
		rlCardRoot.setCameraDistance(scale);
	}

	@Override
	public void onClick(View v) {
		AnimationSet set=new AnimationSet(true);
		AlphaAnimation alpa=new AlphaAnimation(1,0);
		alpa.setDuration(300);
		set.addAnimation(alpa);
		switch (v.getId()) {
		case R.id.imageView_back:
			cardTurnover(0);
			break;
		case R.id.imageView_front:	            	
			alert.setVisibility(View.GONE);
			alert.startAnimation(set);
			cardTurnover(1);
			break;
		}
	}
}
