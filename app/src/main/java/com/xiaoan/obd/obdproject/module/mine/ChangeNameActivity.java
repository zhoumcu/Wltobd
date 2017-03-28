package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/13 09:42
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ChangeNamePresenter.class)
public class ChangeNameActivity extends BeamBaseActivity<ChangeNamePresenter> {
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.btn_enter)
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_changename);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
