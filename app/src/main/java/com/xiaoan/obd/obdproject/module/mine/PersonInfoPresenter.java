package com.xiaoan.obd.obdproject.module.mine;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.untils.SharedPreferences;

/**
 * author：Administrator on 2016/12/9 15:54
 * company: xxxx
 * email：1032324589@qq.com
 */
public class PersonInfoPresenter extends BeamDataActivityPresenter<PersonInfoActivity,User>{

    private User user;

    @Override
    protected void onResume() {
        super.onResume();
        user = SharedPreferences.getInstance().getUserInfo();
        setData(user);
    }

    @Override
    protected void onCreateView(@NonNull PersonInfoActivity view) {
        super.onCreateView(view);
    }

    public void changeName() {
        getView().startActivity(new Intent(getView(),ChangeNameActivity.class));
    }

    public void quitAccount() {

    }

    public void changePhone() {
        getView().startActivity(new Intent(getView(),ChangePhoneActivity.class));
    }

    public void changeIcon() {

    }
}
