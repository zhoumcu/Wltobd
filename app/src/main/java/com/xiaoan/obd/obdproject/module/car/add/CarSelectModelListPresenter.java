package com.xiaoan.obd.obdproject.module.car.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/14 16:00
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarSelectModelListPresenter extends BeamListActivityPresenter<CarSelectModelListActivity,CarBean>{
    @Override
    protected void onCreate(@NonNull CarSelectModelListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        long id = getView().getIntent().getLongExtra("id", 0);
        initData(id);
    }

    @Override
    protected void onCreateView(@NonNull CarSelectModelListActivity view) {
        super.onCreateView(view);
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getView(),CarAllInfoActivity.class);
                intent.putExtra("id",  ((CarBean)getAdapter().getItem(position)).getId());
                startActivity(intent);
                //finish();
            }
        });
    }
    private void initData(long id) {
        Observable.create(new Observable.OnSubscribe<List<CarBean>>() {
            @Override
            public void call(Subscriber<? super List<CarBean>> subscriber) {
                subscriber.onNext(APP.getInstances().dbHelper.getCarType(id));
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }
}
