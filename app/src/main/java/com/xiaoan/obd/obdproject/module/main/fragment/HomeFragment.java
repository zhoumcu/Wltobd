package com.xiaoan.obd.obdproject.module.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.module.base.ZhouBaseFragment;
import com.xiaoan.obd.obdproject.module.event.MessageEvent;
import com.xiaoan.obd.obdproject.module.main.HomeActivity;
import com.xiaoan.obd.obdproject.utils.BitmapUtils;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:39
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(HomeFragmentPresenter.class)
public class HomeFragment extends ZhouBaseFragment<HomeFragmentPresenter> {

    @BindView(R.id.img_open)
    TextView imgOpen;
    @BindView(R.id.tv_searchcode)
    TextView tvSearchcode;
    @BindView(R.id.tv_faultNote)
    TextView tvFaultNote;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.tv_carname)
    TextView tvCarname;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_autoStart)
    TextView tvAutoStart;
    @BindView(R.id.tv_quietMode)
    TextView tvQuietMode;
    @BindView(R.id.tv_backgroundMode)
    TextView tvBackgroundMode;
    @BindView(R.id.tv_monitorTire)
    TextView tvMonitorTire;
    @BindView(R.id.tv_itinerary)
    TextView tvItinerary;
    @BindView(R.id.tv_rank)
    TextView tvRank;
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.tv_testble)
    TextView tvTestble;
    public HomeActivity context;
    @BindView(R.id.rl_default)
    RelativeLayout rlDefault;
    @BindView(R.id.btn_default)
    TextView btnDefault;
    private View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = (HomeActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.frg_home, container, false);
        }
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        if (event.getIsConnected()) {
            imgOpen.setText("已连接智能盒子");
            hideProgress();
        } else {
            imgOpen.setText("未连接智能盒子");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(null);
        imgOpen.setOnClickListener(view1 -> getPresenter().openTire());
        tvSearchcode.setOnClickListener(view1 -> getPresenter().searchCode());
        tvAutoStart.setOnClickListener(view1 -> getPresenter().setAutoMode());
        tvQuietMode.setOnClickListener(view1 -> getPresenter().setQuietMode());
        tvBackgroundMode.setOnClickListener(view1 -> getPresenter().setBackGroundMode());
        tvMonitorTire.setOnClickListener(view1 -> getPresenter().goMonitorTire());
        tvItinerary.setOnClickListener(view1 -> getPresenter().goItinerary());
        tvRank.setOnClickListener(view1 -> getPresenter().goChart());
        tvActivity.setOnClickListener(view1 -> getPresenter().goAActivity());
        tvTestble.setOnClickListener(view1 -> getPresenter().goNotifyActivity());
        tvFaultNote.setOnClickListener(view1 -> getPresenter().searchCode());
        btnDefault.setOnClickListener(view1 -> getPresenter().goSetDefaultCar());
        tvAutoStart.setTag(1);
        tvQuietMode.setTag(1);
        tvBackgroundMode.setTag(1);
        if(APP.getInstances().mBluetoothLeService.isConnected){
            imgOpen.setText("已连接智能盒子");
        }else{
            imgOpen.setText("未连接智能盒子");
        }
    }

    public void initData(String userCarId) {
        if(TextUtils.isEmpty(userCarId)){
            userCarId = SharedPreferences.getInstance().getString(Constants.USER_CAR_ID, "");
        }
        List<CarBean> carBrands = APP.getInstances().getDaoSession().getCarBeanDao().queryRaw(" WHERE USER_CAR_ID = ?", new String[]{userCarId});
        if(carBrands.size()==0){
            rlDefault.setVisibility(View.GONE);
            btnDefault.setVisibility(View.VISIBLE);
            showConfigDialog();
            return;
        }else {
            rlDefault.setVisibility(View.VISIBLE);
            btnDefault.setVisibility(View.GONE);
        }
        CarBean carBean = carBrands.get(0);
        imgIcon.setImageBitmap(BitmapUtils.getImageFromAssetsFile(getView().getContext(), "logo/" + carBean.getBrandLogo() + ".png"));
        tvCarname.setText(carBean.getCarSeriesName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }

    @Override
    protected void onConfig() {
        getPresenter().goSetDefaultCar();
    }
}
