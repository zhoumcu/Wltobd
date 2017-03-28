package com.xiaoan.obd.obdproject.module.trouble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/17 15:38
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TroubleCodeInfoPresenter.class)
public class TroubleCodeInfoActivity extends BeamDataActivity<TroubleCodeInfoPresenter, FaultCodeBean> {
    @BindView(R.id.tv_troublecode)
    TextView tvTroublecode;
    @BindView(R.id.tv_troubleTitle)
    TextView tvTroubleTitle;
    @BindView(R.id.tv_troubleContent)
    TextView tvTroubleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_troubleinfo);
        ButterKnife.bind(this);
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

    @Override
    public void setData(@Nullable FaultCodeBean data) {
        super.setData(data);
        tvTroublecode.setText("故障编码：" + data.getCode());
        tvTroubleTitle.setText(data.getCdefine());
        tvTroubleContent.setText("内容：" + data.getContentZh());
    }
}
