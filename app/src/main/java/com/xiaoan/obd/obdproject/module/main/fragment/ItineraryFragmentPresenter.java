package com.xiaoan.obd.obdproject.module.main.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.entity.ObdTTDao;
import com.xiaoan.obd.obdproject.module.travel.TravelInfoActivity;
import com.xiaoan.obd.obdproject.server.SchedulerTransform;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.NumberUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;

/**
 * author：Administrator on 2016/12/8 11:52
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ItineraryFragmentPresenter extends BeamListFragmentPresenter<ItineraryFragment,ObdTT>{
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    private String filterDate = NumberUtil.getTime(System.currentTimeMillis()).toString();
    private List<ObdTT> obdTTData = new ArrayList<>();

    @Override
    protected void onCreate(@NonNull ItineraryFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }
    @Override
    protected void onCreateView(@NonNull ItineraryFragment view) {
        super.onCreateView(view);
        getAdapter().setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(getView().getContext(),TravelInfoActivity.class);
                i.putExtra(Constants.OBD_TT_DATA,obdTTData.get(position));
                getView().startActivity(i);
            }
        });
        getView().title.setText(NumberUtil.getWeekByDateStr(System.currentTimeMillis()));
        getView().title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dateDlg = new DatePickerDialog(getView().getContext(),
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));

                dateDlg.show();
            }
        });
    }
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            getView().title.setText(NumberUtil.getWeekByDateStr(dateAndTime.getTimeInMillis()));
            filterDate = NumberUtil.getTime(dateAndTime.getTimeInMillis()).toString();
            update(filterDate);
        }
    };
    private void update(String filterDate){
        getAdapter().clear();
        obdTTData = APP.getInstances().getDaoSession().getObdTTDao().queryBuilder().where(ObdTTDao.Properties.CreateAtTime.eq(filterDate)).orderDesc(ObdTTDao.Properties.StartTime).list();
        getAdapter().addAll(obdTTData);
        getAdapter().notifyDataSetChanged();
    }
    private void initData(String filterDate){
//        List<ItineraryBean> o = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            o.add(new ItineraryBean("2016-10-23","2016-11-23"));
//        }
//        Observable<List<ItineraryBean>> observable = Observable.just(o);
//        observable.compose(new SchedulerTransform<>())
//                .unsafeSubscribe(getRefreshSubscriber());
        Observable.create(new Observable.OnSubscribe<List<ObdTT>>() {
            @Override
            public void call(Subscriber<? super List<ObdTT>> subscriber) {
                obdTTData = APP.getInstances().getDaoSession().getObdTTDao().queryBuilder().where(ObdTTDao.Properties.CreateAtTime.eq(filterDate)).orderDesc(ObdTTDao.Properties.StartTime).list();
                subscriber.onNext(obdTTData);
                subscriber.onCompleted();
            }
        }).compose(new SchedulerTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        initData(filterDate);
    }
}
