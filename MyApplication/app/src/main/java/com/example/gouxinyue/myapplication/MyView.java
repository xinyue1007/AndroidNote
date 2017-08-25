package com.example.gouxinyue.myapplication;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

public class MyView extends View  {
    private int mPexiWight=0;
	private int mPexiHight=0;
	private int mTimes=1;//1-10
	private int jianju=5;
	private int mWight=40;
	private int mHight=80;
	private int mNum=10;
	private int mTextColor[] = new int[mNum];
	private int mTextSize[] =new int[mNum];
	private Paint mNumPaint=new Paint();
	private ArrayList<Paint> mList  =new ArrayList<Paint>();
    private OnClickListener mOnClickList;

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);
		int all =a.getIndexCount();
		//Toast.makeText(getContext(), "xxxxxxxxxxx "+mList.size()+" ", 3).show();
		for(int j=0; j<mNum;j++){
			Paint p=new Paint();
			Random random = new Random();
			int randomInt = random.nextInt(255);
			int randomInt2= random.nextInt(255);
			int randomInt3= random.nextInt(255);
			mTextColor[j]=a.getColor(R.styleable.MyView_textColorxx,Color.rgb(randomInt,randomInt2, randomInt3));
			mTextSize[j]=(int)a.getDimension(R.styleable.MyView_textSizexx,(int)TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics()));

		p.setColor(mTextColor[j]);
			//p.setColor(Color.rgb(randomInt, randomInt2, randomInt3));
		p.setTextSize(mTextSize[j]);
		
		mList.add(p);
		}
		//mNumPaint.setColor(Color.BLACK);
		//mNumPaint.setTextSize((int)TypedValue.applyDimension(
		//		TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
		//mNumPaint.setTextAlign(Paint.Align.CENTER);
		a.recycle();

	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		for(int i=0;i<mList.size();i++){
			Random random = new Random();
			int randomInt = random.nextInt(100);
		    canvas.drawRect((100+(mWight+jianju)*i)/mTimes, 1000/mTimes, (100+(mWight+jianju)*i+mWight)/mTimes, (80+i*randomInt)/mTimes, mList.get(i));
			Rect rt=new Rect((100+(mWight+jianju)*i)/mTimes, 1000/mTimes, (100+(mWight+jianju)*i+mWight)/mTimes, 1040/mTimes);
			mNumPaint = mList.get(i);
			mNumPaint.setTextSize(10);
			canvas.drawText(String.valueOf((1000-(80+i*randomInt))/mTimes),(100+(mWight+jianju)*i+10)/mTimes, (1000+20)/mTimes,mNumPaint);

		}
		//invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void setTimes(int times){
		mTimes=times;
		invalidate();
	}

	@Override
	public void setOnClickListener(@Nullable OnClickListener l) {
		mOnClickList=l;
	}
}
