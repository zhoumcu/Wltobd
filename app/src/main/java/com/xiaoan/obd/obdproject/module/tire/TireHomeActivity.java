package com.xiaoan.obd.obdproject.module.tire;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.beam.bijection.RequiresPresenter;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.module.base.ZhouBaseActivity;
import com.xiaoan.obd.obdproject.module.event.MessageEvent;
import com.xiaoan.obd.obdproject.module.tire.fragment.CarConditionFragment;
import com.xiaoan.obd.obdproject.module.tire.fragment.TpmsFragment;
import com.xiaoan.obd.obdproject.module.tire.fragment.TravelDistanceFragment;
import com.xiaoan.obd.obdproject.utils.Logger;
import com.xiaoan.obd.obdproject.utils.NumberUtil;
import com.xiaoan.obd.obdproject.widget.CustomViewPager;

import org.greenrobot.eventbus.EventBus;

/**
 * author：Administrator on 2016/12/8 11:36
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TireHomePresenter.class)
public class TireHomeActivity extends ZhouBaseActivity<TireHomePresenter> implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private final static String TAG = TireHomeActivity.class.getSimpleName();
    private CustomViewPager viewPager;
    private AdvancedPagerSlidingTabStrip tabs;
    private RadioButton btnTravel;
    private RadioButton btnVoice;
    private RadioButton btnPower;
    private RadioButton btnAuto;
    private RadioButton btnTpms;
    private RadioButton btnConditions;
    private RadioButton btnNavigation;
    private TextView tvCurrentTime;
    private int currentPosition;
    protected long startTime;
    protected long endTime;
    private TextView tvLinkTime;
    private ImageView imgSound;
    private ImageView imgPower;


    @Override
    public int onCreateView() {
        currentPosition = getIntent().getIntExtra("currentPosition",0);
        return R.layout.activity_main_test_2;
    }

    @Override
    public void onDelayCreate(ViewStub viewStub) {
        startTime = System.currentTimeMillis();
        new TimeThread().start();
        viewStub.inflate();
        initUI(viewStub);
    }

    @Override
    protected void onFinish() {
        endTime = System.currentTimeMillis();
        getPresenter().saveTestObdTTData();
        MessageEvent event = new MessageEvent();
        event.setStop(true);
        EventBus.getDefault().post(event);
    }

    private void initUI(ViewStub viewStub) {
        long current  = System.currentTimeMillis();
        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        tabs = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
        btnTravel = (RadioButton) findViewById(R.id.btn_travel);
        btnVoice = (RadioButton) findViewById(R.id.btn_voice);
        btnPower = (RadioButton) findViewById(R.id.btn_power);
        btnAuto = (RadioButton) findViewById(R.id.btn_auto);
        btnTpms = (RadioButton) findViewById(R.id.btn_tpms);
        btnConditions = (RadioButton) findViewById(R.id.btn_conditions);
        btnNavigation = (RadioButton) findViewById(R.id.btn_navigation);
        tvCurrentTime = (TextView) findViewById(R.id.tv_currentTime);
        tvLinkTime = (TextView) findViewById(R.id.tv_linkTime);
        imgSound = (ImageView) findViewById(R.id.img_sound);
        imgPower = (ImageView) findViewById(R.id.img_power);

        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
//        viewPager.setPageTransformer(true, new DepthPageTransformer());
        Logger.e("time", "耗时："+String.valueOf(System.currentTimeMillis()-current));
        changeFragment(currentPosition);
        btnTravel.setChecked(true);
        btnVoice.setTag(1);
        btnPower.setTag(1);
        imgSound.setTag(1);

        btnPower.setOnClickListener(this);
        btnVoice.setOnClickListener(this);
        btnTpms.setOnClickListener(this);
        btnTravel.setOnClickListener(this);
        btnConditions.setOnClickListener(this);
        btnNavigation.setOnClickListener(this);
        imgPower.setOnClickListener(this);
        imgSound.setOnClickListener(this);
        btnAuto.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_travel:
                changeFragment(0);
                break;
            case R.id.btn_tpms:
                changeFragment(1);
                break;
            case R.id.btn_conditions:
                changeFragment(2);
                break;
            case R.id.btn_auto:
                changeFragment(3);
                break;
            case R.id.btn_navigation:
                changeFragment(4);
                break;
            case R.id.btn_voice:
                if(btnVoice.getTag().equals(1)){
                    btnVoice.setChecked(true);
                    btnVoice.setTag(2);
                    Toast.makeText(this,"静音模式开启！",Toast.LENGTH_SHORT).show();
                }else {
                    btnVoice.setChecked(false);
                    btnVoice.setTag(1);
                    Toast.makeText(this,"静音模式关闭！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_power:
                showNormalDialog();
                break;
            case R.id.img_sound:
                if(imgSound.getTag().equals(1)){
                    imgSound.setTag(2);
                    Toast.makeText(this,"静音模式开启！",Toast.LENGTH_SHORT).show();
                }else {
                    imgSound.setTag(1);
                    Toast.makeText(this,"静音模式关闭！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_power:
                showNormalDialog();
                break;
        }
    }

    private void changeFragment(int i){
        viewPager.setCurrentItem(i);
    }

    private class myPagerAdapter extends FragmentStatePagerAdapter {
        String[] title = {"行程", "胎压监测", "车况"/*, "智能仪表", "导航"*/};
        TravelDistanceFragment mTravelDistanceFragment;
        TpmsFragment mTpmsFragment;
        CarConditionFragment mCarConditionFragment;

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    mTravelDistanceFragment = new TravelDistanceFragment();
                    return mTravelDistanceFragment;
                case 1:
                    mTpmsFragment = new TpmsFragment();
                    return mTpmsFragment;
                case 2:
                    mCarConditionFragment = new CarConditionFragment();
                    return mCarConditionFragment;
                case 3:
                    mTpmsFragment = new TpmsFragment();
                    return mTpmsFragment;
                case 4:
                    mCarConditionFragment = new CarConditionFragment();
                    return mCarConditionFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeRadio(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void changeRadio(int position){
        switch (position) {
            case 0:
                btnTravel.setChecked(true);
                btnTpms.setChecked(false);
                btnConditions.setChecked(false);
                btnAuto.setChecked(false);
                btnNavigation.setChecked(false);
                break;
            case 1:
                btnTravel.setChecked(false);
                btnTpms.setChecked(true);
                btnConditions.setChecked(false);
                btnAuto.setChecked(false);
                btnNavigation.setChecked(false);
                break;
            case 2:
                btnTravel.setChecked(false);
                btnTpms.setChecked(false);
                btnConditions.setChecked(true);
                btnAuto.setChecked(false);
                btnNavigation.setChecked(false);
                break;
            case 3:
                btnTravel.setChecked(false);
                btnTpms.setChecked(false);
                btnConditions.setChecked(false);
                btnAuto.setChecked(true);
                btnNavigation.setChecked(false);
                break;
            case 4:
                btnTravel.setChecked(false);
                btnTpms.setChecked(false);
                btnConditions.setChecked(false);
                btnAuto.setChecked(false);
                btnNavigation.setChecked(true);
                break;
        }
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private float MIN_SCALE = 0.75f;

        @SuppressLint("NewApi")
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when
                // moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
                        * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);

            }
        }
    }
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    long durtureTime = sysTime-startTime;
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);
                    tvCurrentTime.setText(sysTimeStr); //更新时间
                    tvLinkTime.setText(NumberUtil.getTimeExpend(startTime,sysTime));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showNormalDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
}
