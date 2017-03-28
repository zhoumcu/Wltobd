package com.xiaoan.obd.obdproject.module.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.TravelBean;
import com.xiaoan.obd.obdproject.entity.common.ComResult;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.untils.Constants;
import com.xiaoan.obd.obdproject.untils.SharedPreferences;

import net.steamcrafted.loadtoast.LoadToast;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2016/12/9 15:54
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ObdBoxPresenter extends BeamDataActivityPresenter<ObdBoxActivity,TravelBean>{
    private final static String TAG = ObdBoxPresenter.class.getSimpleName();
    private BluetoothLeService mBluetoothLeService;
    @Inject
    ServiceAPI serviceAPI;
    private LoadToast lt;

    @Override
    protected void onCreate(@NonNull ObdBoxActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        lt = new LoadToast(view);
        lt.setTranslationY(850);    // y offset in pixels
        DaggerServiceModelComponent.builder().build().inject(this);
        mBluetoothLeService = APP.getInstances().getService();
    }

    @Override
    protected void onCreateView(@NonNull ObdBoxActivity view) {
        super.onCreateView(view);
        if(SharedPreferences.getInstance().getBoolean(Constants.BINDORUNBIND,false)){
            getView().btnUnbund.setText("绑定");
        }else{
            getView().btnUnbund.setText("解绑");
        }
    }

    public void sendCommand() {
        if(mBluetoothLeService!=null)
            mBluetoothLeService.writeChar6("AT500"+"\r"+"\n");
    }

    public void unBindBox() {
        if(SharedPreferences.getInstance().getBoolean(Constants.BINDORUNBIND,false)){
            lt.setText("正在绑定...");
            lt.show();
            serviceAPI.bindBox(SharedPreferences.getInstance().getString(Constants.TOKEN,""),"1231231","1616")
                    .compose(new SchedulerTransform<>())
                    .unsafeSubscribe(getBindResult);
        }else{
            lt.setText("正在解绑...");
            lt.show();
            serviceAPI.unBindBox(SharedPreferences.getInstance().getString(Constants.TOKEN,""),"1616")
                    .compose(new SchedulerTransform<>())
                    .unsafeSubscribe(getUnBindResult);
        }
    }

    Subscriber<ComResult> getBindResult = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            lt.error();
        }

        @Override
        public void onNext(ComResult comResult) {
            lt.success();
            getView().btnUnbund.setText("解绑");
            SharedPreferences.getInstance().putBoolean(Constants.BINDORUNBIND,true);
        }
    };
    Subscriber<ComResult> getUnBindResult = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            lt.error();
        }

        @Override
        public void onNext(ComResult comResult) {
            lt.success();
            getView().btnUnbund.setText("绑定");
            SharedPreferences.getInstance().putBoolean(Constants.BINDORUNBIND,false);
        }
    };
}
