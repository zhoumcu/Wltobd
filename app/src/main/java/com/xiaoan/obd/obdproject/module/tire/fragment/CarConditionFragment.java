package com.xiaoan.obd.obdproject.module.tire.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataFragment;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.module.tire.TireHomeActivity;
import com.xiaoan.obd.obdproject.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 10:29
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(CarConditionPresenter.class)
public class CarConditionFragment extends BeamDataFragment<CarConditionPresenter, ObdRT> {


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
    @BindView(R.id.tv_troublecode)
    TextView tvTroublecode;
    @BindView(R.id.tv_secondOil)
    TextView tvSecondOil;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_powerVoltage)
    TextView tvPowerVoltage;
    @BindView(R.id.tv_airOpen)
    TextView tvAirOpen;
    @BindView(R.id.tv_surplusOil)
    TextView tvSurplusOil;
    @BindView(R.id.tv_catalayz)
    TextView tvCatalayz;
    @BindView(R.id.tv_airSensor)
    TextView tvAirSensor;
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
    @BindView(R.id.tv_travel)
    TextView tvTravel;
    @BindView(R.id.search_code)
    RelativeLayout searchCode;
    public TireHomeActivity context;
    private View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (TireHomeActivity)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null)
            rootView= inflater.inflate(R.layout.frg_tirecondition, container, false);
        ButterKnife.bind(this, rootView);
//        searchCode.setOnClickListener(view1 -> getPresenter().searchCode());
        return rootView;
    }

    @Override
    public void setData(ObdRT data) {
        super.setData(data);
        if (data ==null) return;
        if(AppManager.isScreenChange(context)){
            tvRevolution.setText(data.getAvgFuel()+"");
            tvAvrSpeed.setText(data.getAvgSpeed()+"");
            tvPay.setText(data.getDrivingFuel()+"");
            tvOrientation.setText(data.getDrivingSpeed()+"");
            tvTravel.setText(data.getTravelMileage()+"");
        }else {
            tvSpeed.setText(data.getDrivingSpeed() + "");
            tvUnit.setText(data.getRpm() + "");
            tvSecondOil.setText(data.getInstantFuel() + "L/100km");
            tvTemp.setText(data.getWaterTemp() + "℃");
            tvPowerVoltage.setText(data.getBvatteryVol() + "v");
            tvAirOpen.setText(data.getThrottle() + "%");
            tvSurplusOil.setText(data.getDrivingFuel() + "");
            tvTroublecode.setText(data.getFaultCodeNum() + "个故障码");
            tvAirSensor.setText(data.getBvatteryVol() + "v");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
