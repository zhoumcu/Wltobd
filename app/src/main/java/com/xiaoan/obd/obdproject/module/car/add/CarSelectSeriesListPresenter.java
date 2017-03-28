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
 * author：Administrator on 2017/1/14 15:42
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarSelectSeriesListPresenter extends BeamListActivityPresenter<CarSelectSeriesListActivity,CarBean>{

    @Override
    protected void onCreate(@NonNull CarSelectSeriesListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        long id = getView().getIntent().getLongExtra("id", 0);
        initData(id);
    }

    @Override
    protected void onCreateView(@NonNull CarSelectSeriesListActivity view) {
        super.onCreateView(view);
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getView(),CarSelectModelListActivity.class);
                intent.putExtra("id", getAdapter().getItem(position).getId());
                startActivity(intent);
                //finish();
            }
        });
    }

    private void initData(long id) {
        Observable.create(new Observable.OnSubscribe<List<CarBean>>() {
            @Override
            public void call(Subscriber<? super List<CarBean>> subscriber) {
                subscriber.onNext(APP.getInstances().dbHelper.getCarSeries(id));
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }
}
