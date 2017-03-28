package com.xiaoan.obd.obdproject.module.trouble;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.expansion.data.BeamDataActivityPresenter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.untils.Constants;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2017/1/17 15:40
 * company: xxxx
 * email：1032324589@qq.com
 */
public class TroubleCodeInfoPresenter extends BeamDataActivityPresenter<TroubleCodeInfoActivity,FaultCodeBean>{
    @Override
    protected void onCreate(@NonNull TroubleCodeInfoActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        initData(getView().getIntent().getIntExtra(Constants.ID,0));
    }

    private void initData(int intExtra) {
        Observable.create(new Observable.OnSubscribe<FaultCodeBean>() {
            @Override
            public void call(Subscriber<? super FaultCodeBean> subscriber) {
                subscriber.onNext(APP.getInstances().dbHelper.searchTroubleCode(intExtra));
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getDataSubscriber());
    }
}
