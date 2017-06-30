package com.xiaoan.obd.obdproject.module.login;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.utils.Logger;
import com.xiaoan.obd.obdproject.utils.ViewServer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/10/28 08:56
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends BeamBaseActivity<LoginPresenter> {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.imgCancel)
    ImageView imgCancel;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.layoutPhone)
    RelativeLayout layoutPhone;
    @BindView(R.id.btn_pwd)
    TextView btnPwd;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    @BindView(R.id.btnRegister)
    LinearLayout btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        ButterKnife.bind(this);
        //测试数据
        phone.setText("13480562458");
        tvPwd.setText("123456");
        btnSure.setOnClickListener(view -> getPresenter().login());
        tvFind.setOnClickListener(view -> getPresenter().findCount());
        btnPwd.setOnClickListener(view -> getPresenter().findPwd());
        btnRegister.setOnClickListener(view -> getPresenter().register());

        ViewServer.get(this).addWindow(this);

        SparseArray<String> array = new SparseArray<>();
        array.put(1,"Lily");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_UI_HIDDEN:
                //释放资源
                Logger.e("LoginActivity111","释放资源");
                break;
        }
    }
}
