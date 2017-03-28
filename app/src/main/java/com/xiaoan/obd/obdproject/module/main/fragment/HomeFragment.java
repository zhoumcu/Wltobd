package com.xiaoan.obd.obdproject.module.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.module.main.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:39
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(HomeFragmentPresenter.class)
public class HomeFragment extends BeamFragment<HomeFragmentPresenter> {

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
    private View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = (HomeActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.frg_home, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        if(APP.getInstances().mBluetoothLeService.isConnected)
            tvNote.append("--已连接--");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }
}
