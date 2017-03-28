package com.xiaoan.obd.obdproject.module.tire.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.data.BeamDataFragmentPresenter;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.server.bluetooth.BluetoothLeService;
import com.xiaoan.obd.obdproject.server.bluetooth.ObdData;
import com.xiaoan.obd.obdproject.untils.Logger;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/17 10:27
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarConditionPresenter extends BeamDataFragmentPresenter<CarConditionFragment,ObdRT>{
    private static final String TAG = CarConditionPresenter.class.getSimpleName();

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123) {
                Observable.create(new Observable.OnSubscribe<ObdRT>() {
                    @Override
                    public void call(Subscriber<? super ObdRT> subscriber) {
                        subscriber.onNext((ObdRT) msg.obj);
                        subscriber.onCompleted();
                    }
                }).compose(new SchedulerTransform<>()).unsafeSubscribe(getDataSubscriber());
            }
        };
    };

    @Override
    protected void onCreate(@NonNull CarConditionFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
//        getView().getActivity().registerReceiver(broadcastReceiver,new IntentFilter(BluetoothLeService.ACTION_DATA_AVAILABLE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        getView().getActivity().unregisterReceiver(broadcastReceiver);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logger.e(TAG,"前端接收数据："+intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
//            ObdData.execute(intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
            Logger.e(TAG,"处理数据："+ObdData.RT.toString());
            Message msg = new Message();
            msg.what=0x123;
            msg.obj = ObdData.RT;
            handler.sendMessage(msg);
        }
    };
//    public void searchCode() {
//        if(!APP.getInstances().mBluetoothLeService.isConnected) {
//            JUtils.Toast("蓝牙服务未连接成功！");
//            return;
//        }else{
//            APP.getInstances().mBluetoothLeService.writeChar6("AT400"+"\r"+"\n");
//        }
//        BluetoothLeService mBluetoothLeService = getView().context.getPresenter().mBluetoothLeService;
//        if(mBluetoothLeService!=null)
//            mBluetoothLeService.writeChar6("AT400"+"\r"+"\n");
//        this.getView().startActivity(new Intent(this.getView().getActivity(), TroubleCodeSearchActivity.class));
//    }
}
