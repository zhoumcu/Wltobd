package com.xiaoan.obd.obdproject.module;

import android.os.Bundle;

import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;

import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/10/28 08:56
 * company: xxxx
 * email：1032324589@qq.com
 */
public class SplashActivity extends BeamBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splash);
        ButterKnife.bind(this);
    }

}
