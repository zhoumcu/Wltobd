package com.xiaoan.obd.obdproject.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jude.beam.bijection.BeamFragment;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.module.main.fragment.chart.MileageFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.chart.OilWeakFragment;
import com.xiaoan.obd.obdproject.module.main.fragment.chart.WeekFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:43
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChartFragment extends BeamFragment {

    @BindView(R.id.webView)
    WebView webview;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null)
            rootView = inflater.inflate(R.layout.frg_chart, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWebView();
    }

    private void initWebView() {
        WebSettings webSettings =   webview .getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //get the newProgress and refresh progress bar
            }
        });
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl("http://api.xiaoan360.com/seniority/ranked");
    }
    @Deprecated
    private class myPagerAdapter extends FragmentPagerAdapter {
        String[] title = {"本周之最", "油耗排行", "里程排行"};
        WeekFragment mWeekFragment;
        OilWeakFragment mOilWeakFragment;
        MileageFragment mMileageFragment;

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    mWeekFragment = new WeekFragment();
                    return mWeekFragment;
                case 1:
                    mOilWeakFragment = new OilWeakFragment();
                    return mOilWeakFragment;
                case 2:
                    mMileageFragment = new MileageFragment();
                    return mMileageFragment;
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
}
