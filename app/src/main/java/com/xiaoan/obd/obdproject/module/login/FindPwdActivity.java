package com.xiaoan.obd.obdproject.module.login;

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
 * author：Administrator on 2016/10/28 08:56
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(FindPwdPresenter.class)
public class FindPwdActivity extends BeamBaseActivity<FindPwdPresenter> {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.btn_sendcode)
    TextView btnSendcode;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.btnSummit)
    Button btnSummit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_findpwd);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnSendcode.setOnClickListener(view -> getPresenter().sendCode());
        btnSummit.setOnClickListener(view -> getPresenter().findPwd());
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
