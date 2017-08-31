package com.example.gouxinyue.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;

public class TestActivity extends Activity {
    private Button mButton;
    private Context mContext;
    private MyView mMyView;
    Random mRandom=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext=TestActivity.this;
       // FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
       //         FrameLayout.LayoutParams.WRAP_CONTENT,
      //          FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告出现的位置(悬浮于顶部)
        //params.topMargin = 0;
       // params.gravity = Gravity.CENTER_HORIZONTAL;
      //  addContentView(new MyView2(mContext), params);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        mButton = (Button) findViewById(R.id.button);
        mMyView= (MyView) findViewById(R.id.myview);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMyView.setTimes( mRandom.nextInt(10));
               // setResult(1,new Intent());
                //finish();
                //startActivityForResult(new Intent(TestActivity.this,TestActivity2.class),10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);
    }
}
