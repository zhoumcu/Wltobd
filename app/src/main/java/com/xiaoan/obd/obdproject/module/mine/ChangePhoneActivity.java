package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/13 11:11
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ChangePhonePresenter.class)
public class ChangePhoneActivity extends BeamBaseActivity<ChangePhonePresenter> {
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.btn_sendcode)
    TextView btnSendcode;
    @BindView(R.id.btn_enter)
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_changephone);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnSendcode.setOnClickListener(view -> getPresenter().sendCode());
        btnEnter.setOnClickListener(view -> getPresenter().enterChange());
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
