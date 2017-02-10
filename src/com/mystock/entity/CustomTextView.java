package com.mystock.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		degrees = attrs.getAttributeIntValue(NAMESPACE, ATTR_ROTATE, DEFAULTVALUE_DEGREES);
	}

	private  static  final  String  NAMESPACE = "http://schemas.android.com/apk/res/android";
	private  static  final  String  ATTR_ROTATE="rotate";
	private  static  final  int  DEFAULTVALUE_DEGREES = 0;
	private  int  degrees ;

	@Override
	protected  void  onDraw(Canvas canvas) {

		canvas.rotate(degrees,getMeasuredWidth()/2,getMeasuredHeight()/2);
		super.onDraw(canvas);
	}

}
