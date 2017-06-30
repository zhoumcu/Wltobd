package com.xiaoan.obd.obdproject.module.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.entity.common.ComResult;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2016/12/12 14:17
 * company: xxxx
 * email：1032324589@qq.com
 */
public class RegisterActivityPresenter extends Presenter<RegisterActivity>{
    @Inject
    ServiceAPI serviceAPI;
    private int numcode;

    @Override
    protected void onCreate(@NonNull RegisterActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
    }

    public void sendCode() {
        if (getView().edPhone.length() != 11) {
            getView().edPhone.setError("手机号格式错误");
            return;
        }
        numcode = (int) ((Math.random() * 9 + 1) * 100000);
        serviceAPI.sendCode(String.valueOf(numcode),getView().edPhone.getText().toString())
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getSendCodeSubscriber);
    }

    public void register() {
        String edPhone = getView().edPhone.getText().toString();
        if (getView().edAccount.length() <3 || getView().edAccount.length() > 12) {
            getView().edAccount.setError("帐号长度不对，请输入3-12个字符");
            return;
        }
        if(!getView().edPwd.getText().toString().equals(getView().edRepwd.getText().toString())) {
            getView().edRepwd.setError("两次密码输入不对应");
            return;
        }
        if (getView().edPwd.length() < 6 || getView().edPwd.length() > 12) {
            getView().edPwd.setError("密码应为6-12位");
            return;
        }
        if(!TextUtils.isEmpty(edPhone)){
            if (getView().edPhone.length() != 11) {
                getView().edPhone.setError("手机号格式错误");
                return;
            }
            edPhone = null;
        }
        if(!String.valueOf(numcode).equals(getView().edCode.getText().toString())){
            JUtils.Toast("验证码不对，请重新输入");
            return;
        }
        serviceAPI.register(getView().edAccount.getText().toString(),edPhone,edPhone,getView().edPwd.getText().toString())
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getRegisterSubscriber);
    }
    Subscriber<ComResult> getSendCodeSubscriber = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("发送失败！");
            getView().getExpansion().dismissProgressPage();
        }
        @Override
        public void onNext(ComResult result) {
            JUtils.Toast("发送成功！");
        }
    };
    Subscriber<ComResult> getRegisterSubscriber = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("注册失败！"+e.getMessage());
            getView().getExpansion().dismissProgressPage();
        }
        @Override
        public void onNext(ComResult result) {
            JUtils.Toast("注册成功！");
        }
    };
}
