package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.TravelBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ObdBoxPresenter.class)
public class ObdBoxActivity extends BeamDataActivity<ObdBoxPresenter, TravelBean> {
    @BindView(R.id.img_obdBoxIcon)
    ImageView imgObdBoxIcon;
    @BindView(R.id.tv_obdBoxName)
    TextView tvObdBoxName;
    @BindView(R.id.tv_obdBoxSn)
    TextView tvObdBoxSn;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_changeName)
    LinearLayout btnChangeName;
    @BindView(R.id.btn_obdInfo)
    Button btnObdInfo;
    @BindView(R.id.btn_unbund)
    Button btnUnbund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_obdbox);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnObdInfo.setOnClickListener(view -> getPresenter().sendCommand());
        btnUnbund.setOnClickListener(view -> getPresenter().unBindBox());
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
    public void setData(@Nullable TravelBean data) {
        super.setData(data);
    }

}
