package com.xiaoan.obd.obdproject.module.mine;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.untils.Constants;
import com.xiaoan.obd.obdproject.untils.SharedPreferences;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2017/1/13 10:10
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChangeNamePresenter extends Presenter<ChangeNameActivity>{
    @Inject
    ServiceAPI mServiceApi;
    private User user;

    @Override
    protected void onCreateView(@NonNull ChangeNameActivity view) {
        super.onCreateView(view);
        DaggerServiceModelComponent.builder().build().inject(this);
        user = SharedPreferences.getInstance().getUserInfo();
        getView().edName.setText(user.getUserName());
    }

    public void enterChange() {
        if(TextUtils.isEmpty(getView().edName.getText().toString())){
            JUtils.Toast("手机号码不能为空！");
            return;
        }
        mServiceApi.updateName(SharedPreferences.getInstance().getString(Constants.TOKEN,""),"changeName",user.getUserCode(),getView().edName.getText().toString())
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
            JUtils.Toast("修改成功！"+result.getUserName());
//            User user = APP.getInstances().getDaoSession().getUserDao().queryBuilder()
//                    .where(UserDao.Properties.Id.lt(10)).build().unique();
//            if (user == null) {
//                JUtils.Toast("用户不存在!");
//            }else{
//                user.setUserName(getView().edName.getText().toString());
//                APP.getInstances().getDaoSession().getUserDao().update(user);
//            }
            SharedPreferences.getInstance().saveUserInfo(result);
        }
    };
}
