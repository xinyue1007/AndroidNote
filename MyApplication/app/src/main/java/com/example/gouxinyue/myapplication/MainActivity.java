package com.example.gouxinyue.myapplication;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.gouxinyue.myapplication.utils.MyPagerAdapter;

import java.util.ArrayList;



public class MainActivity extends Activity {
    private Context mContext;
    LocalActivityManager manager = null;
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentList;
    private int currIndex;// 当前页卡编号
    private int bmpW;// 横线图片宽度
    private int offset;// 图片移动的偏移量
    float mtep = 0.0f;
    ImageView My_tab_dian;
    private ConstraintLayout mConstraintLayout;
    private Binder mBinder = new IMyAidlInterface.Stub(){
        @Override
        public boolean checkAutoStart(String packageName, String callerApp, String action) throws RemoteException {
            return false;
        }
    };
   // IMyAidlInterface.Stub.asInterface();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);
       // setSupportActionBar(toolbar);
     /*findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent mI=new Intent(MainActivity.this,TestActivity.class);
             startActivityForResult(mI,10);
         }
     });*/
      /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        initView();
        InitImageView();
        initViewpage();
    }

    private void initViewpage(){
        mPager = (ViewPager) findViewById(R.id.viewpager);
        final ArrayList<View> list = new ArrayList<View>();
        Intent intent = new Intent(mContext, TestActivity.class);
        list.add(getView("TestActivity", intent));

        Intent intent3 = new Intent(mContext, TestActivity3.class);
        list.add(getView("TestActivity3", intent3));
        Intent intent2 = new Intent(mContext, TestActivity2.class);
        list.add(getView("TestActivity2", intent2));
        mPager.setAdapter(new MyPagerAdapter(list));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initView(){
        mContext=MainActivity.this;
        My_tab_dian=(ImageView) findViewById(R.id.my_tab_dian);
        //mConstraintLayout=(ConstraintLayout) getLayoutInflater().inflate(R.layout.content_main, null);
       // mConstraintLayout.setOnClickListener(new My_txListener(0));
    }

        /**
     * 初始化动画
     */
    private void InitImageView() {
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.my_tab_dian)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
//        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(offset, 0);
//        ZF_tab_dian.setImageMatrix(matrix);// 设置动画初始位置
        My_tab_dian.setTranslationX(getWindowManager().getDefaultDisplay().getWidth()*3/11);
    }
    /**
     * 通过activity获取视图
     *
     * @param id
     * @param intent
     * @return
     */
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    break;
            }
            Log.d("gxy777","arg0 ="+arg0);
            Log.d("gxy777","currIndex ="+currIndex);
            Log.d("gxy777","one ="+one);
            Log.d("gxy777","two ="+two);
            currIndex = arg0;
//			animation.setFillAfter(true);// True:图片停在动画结束位置
//			animation.setDuration(300);
            //ZF_tab_dian.startAnimation(animation);
			/*zf_tab_dian_layout.startAnimation(animation);*/
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if(arg0 == 0) {
                My_tab_dian.setTranslationX(getWindowManager().getDefaultDisplay().getWidth()*3/11+getWindowManager().getDefaultDisplay().getWidth()*arg1*0.5f);
                mtep = My_tab_dian.getTranslationX();
            } else {
                My_tab_dian.setTranslationX(mtep - getWindowManager().getDefaultDisplay().getWidth()*arg1*0.5f);
            }

            My_tab_dian.setRotationX(arg1);
            Log.i("gxy_tag", "arg1 =" + getWindowManager().getDefaultDisplay().getWidth()*arg1);
            Log.i("gxy_tag", "arg0 =" + arg0);
            Log.i("gxy_tag", "arg2 =" + arg2);
            Log.i("gxy_tag", "tmp =" + mtep);
            Log.i("gxy_tag", "----------------------------");

        }
    }

    public class My_txListener implements View.OnClickListener {
        private int index = 0;

        public My_txListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mPager.setCurrentItem(index);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
