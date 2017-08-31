package com.example.gouxinyue.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by gouxinyue on 2017/8/22.
 */

public class MyView3 extends View {
    private Paint mPaintCircle = new Paint();
    private Paint mPaintArc = new Paint();
    private Paint mPaint1 = new Paint();
    private Paint mPaint2 = new Paint();
    private Path mPath1 = new Path();
    private Path mPath2 = new Path();
    private boolean mAnimate;
    private long mNextTime;
    // angle route
    private long mCount = 0;
    private final float RADIUS = 200;
    private final float COUNT_MIN = 10;
    private final float ANGLE_PASS = 10;

    private float mPreAngle;
    private float mInitAngle;
    private float mStartAngle;
    private float mStopAngle;
    private float mAngle;
    private float[] mOrientation = new float[3];


    final float rad2deg = (float) (180.0f / Math.PI);
    final float num[]=new float[]{0f,90f,180f,270f,360f};
    Random mRandom=new Random();
    public MyView3(Context context) {
        super(context);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.WHITE);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(5.0f);

        mPaintArc.setAntiAlias(true);
        mPaintArc.setColor(Color.BLUE);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setStrokeWidth(14.0f);

        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.GRAY);
        mPaint2.setStyle(Paint.Style.FILL);

        // Construct a wedge-shaped path
        mPath1.moveTo(0, -120);
        mPath1.lineTo(-40, 100);
        mPath1.lineTo(0, 80);
        mPath1.close();

        mPath2.moveTo(0, -120);
        mPath2.lineTo(40, 100);
        mPath2.lineTo(0, 80);
        mPath2.close();

    }

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
    }

    public void setOrientation(){

        mOrientation[0]=num[mRandom.nextInt(4)];
        invalidate();

    }
    @Override
    protected void onDraw(Canvas canvas) {
        mOrientation[0] = (mOrientation[0] * rad2deg);
        mOrientation[1] = (mOrientation[1] * rad2deg);
        mOrientation[2] = -(mOrientation[2] * rad2deg);
        canvas.drawColor(Color.BLACK);

        int w = getWidth();
        int h = getHeight();
        Log.v("xxxx", "width = " + w);
        Log.v("xxxx", "height = " + h);
        int cx = w / 2;
        int cy = h / 2;

        canvas.translate(cx, cy);

        canvas.drawCircle(0, 0, RADIUS, mPaintCircle);
        if (mCount >= COUNT_MIN) {
            float w1 = 9;
            RectF rtArc = new RectF(-RADIUS - w1, -RADIUS - w1,
                    RADIUS + w1, RADIUS + w1);
            canvas.drawArc(rtArc, -(mInitAngle + mStartAngle + 90),
                    -(mStopAngle - mStartAngle), false, mPaintArc);
        }

        if (mOrientation != null) {
            canvas.rotate(-mOrientation[0]);
        }
        canvas.drawPath(mPath1, mPaint1);
        canvas.drawPath(mPath2, mPaint2);

    }

    @Override
    protected void onAttachedToWindow() {
        mAnimate = true;
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mAnimate = false;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(400,400, 400, 400);
    }
};
