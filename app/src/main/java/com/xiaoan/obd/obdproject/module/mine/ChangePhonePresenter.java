package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.entity.common.ComResult;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2017/1/13 11:25
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChangePhonePresenter extends Presenter<ChangePhoneActivity>{
    @Inject
    ServiceAPI mServiceAPI;
    private int numcode;
    private User user;

    @Override
    protected void onCreate(@NonNull ChangePhoneActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
    }

    @Override
    protected void onCreateView(@NonNull ChangePhoneActivity view) {
        super.onCreateView(view);
        user = SharedPreferences.getInstance().getUserInfo();
    }

    public void sendCode() {
        if (getView().edPhone.length() != 11) {
            getView().edPhone.setError("手机号格式错误");
            return;
        }
        numcode = (int) ((Math.random() * 9 + 1) * 100000);
        mServiceAPI.sendCode(String.valueOf(numcode),getView().edPhone.getText().toString())
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getSendCodeSubscriber);
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

    public void enterChange() {
        if(!String.valueOf(numcode).equals(getView().edCode.toString())){
            JUtils.Toast("验证码不对，请重新输入");
            return;
        }
        mServiceAPI.updatePhone(SharedPreferences.getInstance().getString(Constants.TOKEN,""),"changePhone",user.getUserCode(),getView().edPhone.getText().toString())
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getChangeSubscriber);
    }

    Subscriber<User> getChangeSubscriber = new Subscriber<User>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("修改失败！");
            getView().getExpansion().dismissProgressPage();
        }
        @Override
        public void onNext(User result) {
            JUtils.Toast("修改成功！"+result.getPhone());
//            User user = APP.getInstances().getDaoSession().getUserDao().queryBuilder()
//                    .where(UserDao.Properties.Id.lt(10)).build().unique();
//            if (user == null) {
//                JUtils.Toast("用户不存在!");
//            }else{
//                user.setPhone(getView().edPhone.getText().toString());
//                APP.getInstances().getDaoSession().getUserDao().update(user);
//            }
            SharedPreferences.getInstance().saveUserInfo(result);
        }
    };
}
