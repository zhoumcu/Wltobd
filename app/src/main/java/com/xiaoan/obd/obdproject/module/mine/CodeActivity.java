package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;

/**
 * author：Administrator on 2017/4/26 15:19
 * company: xxxx
 * email：1032324589@qq.com
 */

public class CodeActivity extends BeamBaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_code);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
