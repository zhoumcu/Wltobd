package com.xiaoan.obd.obdproject.module.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.common.ComResult;
import com.xiaoan.obd.obdproject.module.car.add.CarSelectBrandListActivity;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.untils.Constants;
import com.xiaoan.obd.obdproject.untils.SharedPreferences;

import net.steamcrafted.loadtoast.LoadToast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2016/12/12 10:47
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarListPresenter extends BeamListActivityPresenter<CarListActivity,CarBean>{
    List<CarBean> list = new ArrayList<>();
    @Inject
    ServiceAPI serviceAPI;

    private BroadcastReceiver broadcastReceviver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constants.REFRESH_LIST)){
                onRefresh();
            }else if(intent.getAction().equals(Constants.DELETE_LIST)){
                lt.setText("正在删除...");
                lt.show();
                serviceAPI.deleteCarInfo(SharedPreferences.getInstance().getString(Constants.TOKEN,""),intent.getStringExtra(Constants.ID))
                        .compose(new SchedulerTransform<>()).unsafeSubscribe(getDeleteSubscribe);
            }
        }
    };
    private LoadToast lt;

    @Override
    protected void onCreateView(@NonNull CarListActivity view) {
        super.onCreateView(view);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.REFRESH_LIST);
        intentFilter.addAction(Constants.DELETE_LIST);
        getView().registerReceiver(broadcastReceviver,intentFilter);
        DaggerServiceModelComponent.builder().build().inject(this);
        lt = new LoadToast(view);
        lt.setTranslationY(850);    // y offset in pixels
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getView().unregisterReceiver(broadcastReceviver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        Observable.create(new Observable.OnSubscribe<List<CarBean>>() {
            @Override
            public void call(Subscriber<? super List<CarBean>> subscriber) {
                subscriber.onNext(APP.getInstances().getDaoSession().getCarBeanDao().loadAll());
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }

    public void goAddCarActivity() {
        getView().startActivity(new Intent(getView(), CarSelectBrandListActivity.class));
    }


    Subscriber<ComResult> getDeleteSubscribe = new Subscriber<ComResult>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("上传失败！"+e.getMessage());
            lt.error();
            getView().getExpansion().dismissProgressPage();
        }
        @Override
        public void onNext(ComResult result) {
            JUtils.Toast("上传成功！");
            getView().carListHodlder.setDelete();
            lt.success();
        }
    };
}
