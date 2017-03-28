package com.xiaoan.obd.obdproject.module.car;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;

/**
 * author：Administrator on 2016/12/12 10:45
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(CarListPresenter.class)
public class CarListActivity extends BeamListActivity<CarListPresenter,CarBean>{
    public CarListViewHolder carListHodlder;

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        carListHodlder = new CarListViewHolder(parent);
        return carListHodlder;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(false)
                .setRefreshAble(true)
                .setNoMoreAble(false)
                .setErrorAble(true)
                .setErrorTouchToResumeAble(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            case R.id.menu_add:
                getPresenter().goAddCarActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //调用inflate()方法创建菜单
        getMenuInflater().inflate(R.menu.car_menu,menu);
        //如果返回false，创建的菜单无法显示
        return true;
    }
}
