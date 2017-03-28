package com.xiaoan.obd.obdproject.module.trouble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.untils.Constants;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/17 15:08
 * company: xxxx
 * email：1032324589@qq.com
 */

public class TroubleCodeSearchPresenter extends BeamListActivityPresenter<TroubleCodeSearchActivity,FaultCodeBean>{

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.getStringExtra(BluetoothLeService.EXTRA_DATA);
        }
    };

    @Override
    protected void onCreate(@NonNull TroubleCodeSearchActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        initData();
        getView().registerReceiver(broadcastReceiver,new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    @Override
    protected void onCreateView(@NonNull TroubleCodeSearchActivity view) {
        super.onCreateView(view);
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getView(),TroubleCodeInfoActivity.class);
                intent.putExtra(Constants.ID,getAdapter().getItem(position).getId());
                getView().startActivity(intent);
            }
        });
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<List<FaultCodeBean>>() {
            @Override
            public void call(Subscriber<? super List<FaultCodeBean>> subscriber) {
                subscriber.onNext(APP.getInstances().dbHelper.searchTroubleCode("P1000"));
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getView().unregisterReceiver(broadcastReceiver);
    }
}
