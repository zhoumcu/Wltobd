package com.xiaoan.obd.obdproject.module.tire.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataFragment;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.untils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 10:27
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TravelDistancePrensenter.class)
public class TravelDistanceFragment extends BeamDataFragment<TravelDistancePrensenter, ObdRT> {
    @BindView(R.id.btnRight)
    TextView btnRight;
    @BindView(R.id.img_sound)
    ImageView imgSound;
    @BindView(R.id.img_power)
    ImageView imgPower;
    @BindView(R.id.tv_linkTime)
    TextView tvLinkTime;
    @BindView(R.id.tv_currentTime)
    TextView tvCurrentTime;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.tv_travelDis)
    TextView tvTravelDis;
    @BindView(R.id.tv_speedTra)
    TextView tvSpeedTra;
    @BindView(R.id.tv_oilWeak)
    TextView tvOilWeak;
    @BindView(R.id.tv_space)
    TextView tvSpace;
    @BindView(R.id.tv_speedLand)
    TextView tvSpeedLand;
    @BindView(R.id.tv_revolution)
    TextView tvRevolution;
    @BindView(R.id.tv_avrSpeed)
    TextView tvAvrSpeed;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_orientation)
    TextView tvOrientation;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long current  = System.currentTimeMillis();
        if(rootView==null)
            rootView = inflater.inflate(R.layout.frg_tiretravel, container, false);
        ButterKnife.bind(this, rootView);
        Logger.e("time", "耗时1："+String.valueOf(System.currentTimeMillis()-current));
        return rootView;
    }

    @Override
    public void setData(ObdRT data) {
        if(data==null) return;
        tvSpeed.setText(data.getDrivingSpeed()+"");
        tvTravelDis.setText(data.getTravelMileage()+"km");
        tvSpeedTra.setText(data.getAvgSpeed()+"km/h");
        tvOilWeak.setText(data.getDrivingFuel()+"L");
        tvSpace.setText(data.getDrivingSpeed()+"元");
        tvSpeedLand.setText(data.getDrivingSpeed()+"");
        tvRevolution.setText(data.getRpm()+"");
        tvAvrSpeed.setText(data.getAvgSpeed()+"");
        tvPay.setText(data.getDrivingSpeed()+"");
        tvOrientation.setText(data.getDrivingSpeed()+"");
        tvTemp.setText(data.getWaterTemp()+"");
    }
}
