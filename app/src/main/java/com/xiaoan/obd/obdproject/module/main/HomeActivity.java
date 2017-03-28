package com.xiaoan.obd.obdproject.module.main;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.utils.JUtils;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.module.main.fragment.ChartFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.HomeFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.ItineraryFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.MaintenanceFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:36
 * company: xxxx
 * email：1032324589@qq.com
 */
public class HomeActivity extends BeamBaseActivity {
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
//        tabs.showDot(0, "99+");
    }
    private class myPagerAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider{
        String[] title = { "首页", "行程", "保养","排行","我的" };
        int[] iconNo = {R.mipmap.ico_home, R.mipmap.ico_walk, R.mipmap.ico_mode, R.mipmap.ico_server,R.mipmap.ico_user };
        int[] iconSelt = {R.mipmap.ico_home_c, R.mipmap.ico_walk_c,R.mipmap.ico_mode_c,R.mipmap.ico_server_c,R.mipmap.ico_user_c };
        HomeFragment mHomeFragment;
        ItineraryFragment mItineraryFragment;
        MaintenanceFragment mMaintenanceFragment;
        ChartFragment mChartFragment;
        MineFragment mMineFragment;
        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    mHomeFragment = new HomeFragment();
                    return mHomeFragment;
                case 1:
                    mItineraryFragment = new ItineraryFragment();
                    return mItineraryFragment;
                case 2:
                    mMaintenanceFragment = new MaintenanceFragment();
                    return mMaintenanceFragment;
                case 3:
                    mChartFragment = new ChartFragment();
                    return mChartFragment;
                case 4:
                    mMineFragment = new MineFragment();
                    return mMineFragment;
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

        @Override
        public Integer getPageIcon(int position) {
            return iconNo[position];
        }

        @Override
        public Integer getPageSelectIcon(int position) {
            return iconSelt[position];
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                JUtils.Toast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
