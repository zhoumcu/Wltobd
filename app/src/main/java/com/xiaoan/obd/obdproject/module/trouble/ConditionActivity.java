package com.xiaoan.obd.obdproject.module.trouble;

import android.os.Bundle;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;

/**
 * author：Administrator on 2016/12/8 11:42
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ConditionActivityPresenter.class)
public class ConditionActivity extends BeamBaseActivity<ConditionActivityPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_condition);
    }
}
