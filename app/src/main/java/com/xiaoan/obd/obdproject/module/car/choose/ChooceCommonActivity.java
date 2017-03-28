package com.xiaoan.obd.obdproject.module.car.choose;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.entity.CarBean;

/**
 * author：Administrator on 2017/1/16 17:22
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ChooceCommonPresenter.class)
public class ChooceCommonActivity extends BeamListActivity<ChooceCommonPresenter,CarBean> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public BaseViewHolder<CarBean> getViewHolder(ViewGroup parent, int viewType) {
        return new ChooceCommonHolder(parent);
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
