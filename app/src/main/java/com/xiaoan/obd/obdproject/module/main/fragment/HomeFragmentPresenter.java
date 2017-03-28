package com.xiaoan.obd.obdproject.module.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.jude.beam.bijection.Presenter;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.module.tire.TireHomeActivity;
import com.xiaoan.obd.obdproject.module.trouble.ConditionActivity;
import com.xiaoan.obd.obdproject.module.trouble.TroubleCodeSearchActivity;
import com.xiaoan.obd.obdproject.server.DaggerServiceModelComponent;
import com.xiaoan.obd.obdproject.server.ServiceAPI;
import com.xiaoan.obd.obdproject.server.UploadDataServer;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * author：Administrator on 2016/12/8 11:47
 * company: xxxx
 * email：1032324589@qq.com
 */
public class HomeFragmentPresenter extends Presenter<HomeFragment> {
    private static final java.lang.String TAG = HomeFragmentPresenter.class.getSimpleName();
    @Inject
    ServiceAPI serviceAPI;

    @Override
    protected void onCreate(@NonNull HomeFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        DaggerServiceModelComponent.builder().build().inject(this);
        Intent intent = new Intent(getView().getActivity(), UploadDataServer.class);
        getView().getActivity().startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openTire(){
        this.getView().startActivity(new Intent(this.getView().getActivity(), TireHomeActivity.class));
    }

    public void searchCode() {
        this.getView().startActivity(new Intent(this.getView().getActivity(), TroubleCodeSearchActivity.class));
    }
    public void setAutoMode(){

    }

    public void setQuietMode() {

    }
    public void setBackGroundMode(){

    }

    public void goMonitorTire() {
        this.getView().startActivity(new Intent(this.getView().getActivity(), TroubleCodeSearchActivity.class));
    }
    public void goItinerary(){
        this.getView().startActivity(new Intent(this.getView().getActivity(), ConditionActivity.class));
    }

    public void goChart() {
        getView().context.viewPager.setCurrentItem(3);
    }
    public void goAActivity() {
        Toast.makeText(getView().getActivity(),"陆续开发中。。。",Toast.LENGTH_SHORT).show();
    }
    public void goNotifyActivity() {
        Toast.makeText(getView().getActivity(),"陆续开发中。。。",Toast.LENGTH_SHORT).show();
    }

    Subscriber<Object> getUploadRTSubscriber = new Subscriber<Object>() {

        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            JUtils.Toast("上传RT数据失败！"+e.getMessage());
//            ObdData.RT.setId(null);
//            APP.getInstances().getDaoSession().insert(ObdData.RT);
        }
        @Override
        public void onNext(Object comResult) {
            JUtils.Toast("上传RT数据成功！");
        }
    };
    Subscriber<Object> getUploadTTSubscriber = new Subscriber<Object>() {

        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onNext(Object user) {

        }
    };
}
