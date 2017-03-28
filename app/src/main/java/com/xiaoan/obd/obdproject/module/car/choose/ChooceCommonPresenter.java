package com.xiaoan.obd.obdproject.module.car.choose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.untils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/16 17:40
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChooceCommonPresenter extends BeamListActivityPresenter<ChooceCommonActivity,CarBean> {
    private String type;
    private String[] oilType= {"0#柴油","93#(北京92#)","97#(北京95#)","90#","98#"};
    @Override
    protected void onCreate(@NonNull ChooceCommonActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        type = getView().getIntent().getStringExtra(Constants.CHANGE_TYPE);
        initData();
    }

    @Override
    protected void onCreateView(@NonNull ChooceCommonActivity view) {
        super.onCreateView(view);
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getView(),ChangeActivity.class);
                intent.putExtra(Constants.CHANGE_CONTENT,getAdapter().getItem(position).getBrandCname());
                getView().setResult(1002,intent);
                getView().finish();
            }
        });
    }

    private void initData() {
        if(type.equals(Constants.TYRE)){
            Observable.create(new Observable.OnSubscribe<List<CarBean>>() {
                @Override
                public void call(Subscriber<? super List<CarBean>> subscriber) {
                    subscriber.onNext(APP.getInstances().dbHelper.getCarLunTaiTypeList());
                    subscriber.onCompleted();
                }
            }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
        }else {
            Observable.create(new Observable.OnSubscribe<List<CarBean>>() {
                @Override
                public void call(Subscriber<? super List<CarBean>> subscriber) {
                    subscriber.onNext(getString());
                    subscriber.onCompleted();
                }
            }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
        }
    }
    private List<CarBean> getString(){
        List<CarBean> carList = new ArrayList<>();
        for (String s: oilType) {
            CarBean car = new CarBean();
            car.setBrandCname(s);
            carList.add(car);
        }
        return carList;
    }
}
