package com.example.gouxinyue.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class TestActivity2 extends Activity {
    private Button mButton;
    private CollectionCurveView mCollectView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        mCollectView = (CollectionCurveView) findViewById(R.id.coll);
        Random random=new Random();
        int [] mun=new int[mCollectView.COUNT];
        for(int i=0;i<mCollectView.COUNT;i++){
            mun[i]=random.nextInt(mCollectView.MAX_DIS);
        }

        mCollectView.setDistance(mun);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mButton = (Button) findViewById(R.id.button2);
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
                setResult(RESULT_FIRST_USER,new Intent());
                finish();
            }
        });
    }

}
