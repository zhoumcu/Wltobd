package com.xiaoan.obd.obdproject.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * author：Administrator on 2016/12/9 09:42
 * company: xxxx
 * email：1032324589@qq.com
 */

public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }


    @Override
    public void scrollTo(int x, int y) {
        if (isCanScroll) {
            super.scrollTo(x, y);
        }
    }
}
