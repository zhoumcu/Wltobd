package com.xiaoan.obd.obdproject.module.car.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.module.car.choose.ChangeActivity;
import com.xiaoan.obd.obdproject.module.car.choose.ChooceCommonActivity;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.utils.AppManager;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import net.steamcrafted.loadtoast.LoadToast;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/16 15:47
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarAllInfoPresenter extends BeamDataActivityPresenter<CarAllInfoActivity,CarBean>{

    private static final int REQUESTCODE = 1001;
    private static final int REQUESTCODE1 = 1003;
    private static final int REQUESTCODE2 = 1004;
    private static final int REQUESTCODE3 = 1005;
    private static final int REQUESTCODE4 = 1006;
    private boolean type;
    @Inject
    ServiceAPI serviceAPI;
    private CarBean carBean;
    private LoadToast lt;

    @Override
    protected void onCreate(@NonNull CarAllInfoActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
        carBean = (CarBean) getView().getIntent().getSerializableExtra("id");
        type = getView().getIntent().getExtras().getBoolean(Constants.TYRE);
        lt = new LoadToast(view);
        lt.setTranslationY(850);    // y offset in pixels
        initData(type);
    }
    private void initData(boolean type){
        if(!type){
            carBean.setBrandLogo(SharedPreferences.getInstance().getString("logo",""));
            Observable.create(new Observable.OnSubscribe<CarBean>() {
                @Override
                public void call(Subscriber<? super CarBean> subscriber) {
                    subscriber.onNext(carBean);
                    subscriber.onCompleted();
                }
            }).compose(new SchedulerTransform<>()).unsafeSubscribe(getDataSubscriber());
        }else {
            Observable.create(new Observable.OnSubscribe<CarBean>() {
                @Override
                public void call(Subscriber<? super CarBean> subscriber) {
                    subscriber.onNext(carBean);
                    subscriber.onCompleted();
                }
            }).compose(new SchedulerTransform<>()).unsafeSubscribe(getDataSubscriber());
        }
    }

    public void chooceFromTyre() {
        Intent intent = new Intent(getView(),ChooceCommonActivity.class);
        intent.putExtra(Constants.CHANGE_TYPE,Constants.TYRE);
        getView().startActivityForResult(intent,REQUESTCODE);
    }

    public void chooceBackTyre() {
        Intent intent = new Intent(getView(),ChooceCommonActivity.class);
        intent.putExtra(Constants.CHANGE_TYPE,Constants.TYRE);
        getView().startActivityForResult(intent,REQUESTCODE1);
    }

    public void chooceOilType() {
        Intent intent = new Intent(getView(),ChooceCommonActivity.class);
        intent.putExtra(Constants.CHANGE_TYPE,Constants.OIL_TYPE);
        getView().startActivityForResult(intent,REQUESTCODE2);
    }

    public void chooceOilPrice() {
        Intent intent = new Intent(getView(),ChangeActivity.class);
        intent.putExtra(Constants.CHANGE_CONTENT,getView().tvFueloilprice.getText().toString());
        intent.putExtra(Constants.CHANGE_TYPE,Constants.OIL_PRICE);
        getView().startActivityForResult(intent,REQUESTCODE3);
    }

    public void chooceOilWeight() {
        Intent intent = new Intent(getView(),ChangeActivity.class);
        intent.putExtra(Constants.CHANGE_CONTENT,getView().tvOilL.getText().toString());
        intent.putExtra(Constants.CHANGE_TYPE,Constants.OIL_WEIGHT);
        getView().startActivityForResult(intent,REQUESTCODE4);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if(resultCode==1002){
            String content = data.getStringExtra(Constants.CHANGE_CONTENT);
            switch (requestCode){
                case REQUESTCODE:
                    getView().tvFromtire.setText(content);
                    break;
                case REQUESTCODE1:
                    getView().tvBacktire.setText(content);
                    break;
                case REQUESTCODE2:
                    getView().tvFueloil.setText(content);
                    break;
                case REQUESTCODE3:
                    getView().tvFueloilprice.setText(content);
                    break;
                case REQUESTCODE4:
                    getView().tvOilL.setText(content);
                    break;
            }
        }
    }

    public void saveCarInfo() {
        boolean type1 = type;
        lt.setText("正在保存...");
        lt.show();
        User user = SharedPreferences.getInstance().getUserInfo();
        if(!type1){
            carBean.setUserCode(user.getUserCode());
            carBean.setUserCarID(user.getUserCode()+ System.currentTimeMillis());
        }
        carBean.setBtyre(getView().tvBacktire.getText().toString());
        carBean.setFtyre(getView().tvFromtire.getText().toString());
        carBean.setFuelType(getView().tvFueloil.getText().toString());
        carBean.setOilPrice(getView().tvFueloilprice.getText().toString());
        carBean.setTank(getView().tvOilL.getText().toString());
        String json = new Gson().toJson(carBean);
        serviceAPI.uploadCarInfo(json)
        .compose(new SchedulerTransform<>()).unsafeSubscribe(getUploadSubsribe);
    }

    Subscriber<Object> getUploadSubsribe = new Subscriber<Object>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("上传失败！"+e.getMessage());
            getView().getExpansion().dismissProgressPage();
            lt.error();
        }
        @Override
        public void onNext(Object result) {
            boolean type1 = type;
            JUtils.Toast("上传成功！");
            if(type1){
                APP.getInstances().getDaoSession().insertOrReplace(carBean);
                JUtils.Toast("修改成功！");
                AppManager.getAppManager().finishAllActivity();
            }else {
                APP.getInstances().getDaoSession().insert(carBean);
                JUtils.Toast("保存成功！");
                AppManager.getAppManager().finishAllActivity();
            }
            lt.success();
        }
    };
}
