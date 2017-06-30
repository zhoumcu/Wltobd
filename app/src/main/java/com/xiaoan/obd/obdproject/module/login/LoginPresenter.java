package com.xiaoan.obd.obdproject.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.entity.common.Token;
import com.xiaoan.obd.obdproject.module.main.HomeActivity;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2016/10/28 09:03
 * company: xxxx
 * email：1032324589@qq.com
 */

public class LoginPresenter extends Presenter<LoginActivity>{

    @Inject
    ServiceAPI serviceAPI;
    private String phone;
    private LoadToast lt;

    @Override
    protected void onCreate(@NonNull LoginActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
        lt = new LoadToast(view);
        lt.setTranslationY(950);    // y offset in pixels
    }

    public void login() {
        phone = getView().phone.getText().toString();
        String mPassword = getView().tvPwd.getText().toString();
        if (phone.length() != 11) {
            getView().phone.setError("手机号格式错误");
            return;
        }
        if (mPassword.length() < 6 || mPassword.length() > 12) {
            getView().tvPwd.setError("密码应为6-12位");
            return;
        }
        lt.setText("登录中...");
        lt.show();
        serviceAPI.login(phone,mPassword,null)
                .compose(new SchedulerTransform<>())
                .unsafeSubscribe(getLoginSubscriber);

    }
    public void findPwd() {
        this.getView().startActivity(new Intent(this.getView(), FindPwdActivity.class));
    }
    public void findCount() {
        this.getView().startActivity(new Intent(this.getView(), FindAccountActivity.class));
    }
    public void register() {
        this.getView().startActivity(new Intent(this.getView(), RegisterActivity.class));
    }

    Subscriber<User> getLoginSubscriber = new Subscriber<User>() {

        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onNext(User user) {

            SharedPreferences.getInstance().saveUserInfo(user);
            APP.getInstances().getDaoSession().insert(user);
            //获取token
            serviceAPI.requestToken(phone)
                    .compose(new SchedulerTransform<>())
                    .unsafeSubscribe(getTokenSubscriber);
        }
    };
    Subscriber<Token> getTokenSubscriber = new Subscriber<Token>() {
        @Override
        public void onCompleted() {
        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("获取token失败！"+e.getMessage());
            lt.error();
        }
        @Override
        public void onNext(Token token) {
//            JUtils.Toast("获取token="+token.getToken());
            lt.success();
            SharedPreferences.getInstance().putString(Constants.TOKEN,token.getToken());
            if(!SharedPreferences.getInstance().getBoolean(Constants.IS_FIRST,false)){
                lt.setText("同步信息...");
                lt.show();
                serviceAPI.downLoadCarInfo(token.getToken(),SharedPreferences.getInstance().getUserInfo().getUserCode())
                        .compose(new SchedulerTransform<>()).unsafeSubscribe(getDownSubscriber);
            }else{
                getView().startActivity(new Intent(getView(), HomeActivity.class));
                getView().finish();
            }
        }
    };
    Subscriber<List<CarBean>> getDownSubscriber = new Subscriber<List<CarBean>>() {
        @Override
        public void onCompleted() {
        }
        @Override
        public void onError(Throwable e) {
            if(e.getMessage().contains("首次登录")){
                lt.success();
                SharedPreferences.getInstance().putBoolean(Constants.IS_FIRST,true);
                getView().startActivity(new Intent(getView(), HomeActivity.class));
                getView().finish();
            }else {
                JUtils.Toast("同步更新失败！"+e.getMessage());
                lt.error();
            }
        }
        @Override
        public void onNext(List<CarBean> carBean) {
            lt.success();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (CarBean c: carBean) {
                        APP.getInstances().getDaoSession().insert(c);
                        c.setId(null);
                    }
                }
            }).start();
            SharedPreferences.getInstance().putBoolean(Constants.IS_FIRST,true);
            getView().startActivity(new Intent(getView(), HomeActivity.class));
            getView().finish();
        }
    };
}

