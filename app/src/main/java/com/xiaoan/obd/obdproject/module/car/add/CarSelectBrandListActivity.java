package com.xiaoan.obd.obdproject.module.car.add;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.utils.AppManager;
import com.xiaoan.obd.obdproject.widget.pinyin.ClearEditText;
import com.xiaoan.obd.obdproject.widget.pinyin.SideBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/12 11:04
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(CarSelectBrandListActivityPresenter.class)
public class CarSelectBrandListActivity extends BeamBaseActivity<CarSelectBrandListActivityPresenter> {

    @BindView(R.id.filter_edit)
    ClearEditText filterEdit;
    @BindView(R.id.recycler)
    ListView recycler;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_carbrand);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        AppManager.getAppManager().addActivity(this);
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
