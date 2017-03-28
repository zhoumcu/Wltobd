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
import com.xiaoan.obd.obdproject.server.param.OBDPARAM;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2016/10/28 09:03
 * company: xxxx
 * email：1032324589@qq.com
 */

public class FindPwdPresenter extends Presenter<FindPwdActivity>{
    @Inject
    ServiceAPI serviceAPI;

    @Override
    protected void onCreate(@NonNull FindPwdActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
    }

    public void sendCode(){
        String phone = getView().phone.getText().toString();
        if(TextUtils.isEmpty(phone)) return;
        serviceAPI.sendCode("452364",phone)
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getSendCodeSubscriber);
    }

    public void findPwd() {
        String phone = getView().phone.getText().toString();
        String code = getView().tvCode.getText().toString();
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(code)) return;
        if(!code.equals("452364")) return;
        serviceAPI.findPwd(OBDPARAM.account,phone)
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getFindPwdSubscriber);
    }
    Subscriber<ComResult> getFindPwdSubscriber = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("修改失败！");
            getView().getExpansion().dismissProgressPage();
        }
        @Override
        public void onNext(ComResult result) {
            JUtils.Toast("修改成功！");
            getView().finish();
        }
    };
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
}
