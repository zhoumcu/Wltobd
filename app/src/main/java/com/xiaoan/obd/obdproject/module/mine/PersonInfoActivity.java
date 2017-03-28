package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.untils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(PersonInfoPresenter.class)
public class PersonInfoActivity extends BeamDataActivity<PersonInfoPresenter, User> {
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_changeName)
    LinearLayout btnChangeName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.btn_changeIcon)
    LinearLayout btnChangeIcon;
    @BindView(R.id.btn_changePhone)
    LinearLayout btnChangePhone;
    @BindView(R.id.btn_quitAccount)
    Button btnQuitAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_personinfo);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnChangeName.setOnClickListener(view -> getPresenter().changeName());
        btnChangeIcon.setOnClickListener(view -> getPresenter().changeIcon());
        btnChangePhone.setOnClickListener(view -> getPresenter().changePhone());
        btnQuitAccount.setOnClickListener(view -> getPresenter().quitAccount());
        if(getIntent().getStringExtra("type").equals(Constants.BusinessCard)){
            btnChangePhone.setVisibility(View.GONE);
            btnQuitAccount.setVisibility(View.GONE);
            btnChangeIcon.setEnabled(false);
            btnChangeName.setEnabled(false);
        }
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
    public void setData(@Nullable User data) {
        super.setData(data);
        tvAccount.setText(data.getUserCode());
        tvName.setText(data.getUserName());
        tvPhone.setText(data.getPhone());
    }

}
