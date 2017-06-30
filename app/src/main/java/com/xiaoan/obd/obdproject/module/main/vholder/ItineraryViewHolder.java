package com.xiaoan.obd.obdproject.module.main.vholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.utils.NumberUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 14:42
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ItineraryViewHolder extends BaseViewHolder<ObdTT> {

    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_stopTime)
    TextView tvStopTime;
    @BindView(R.id.tv_averageFuel)
    TextView tvAverageFuel;
    @BindView(R.id.tv_travelMileage)
    TextView tvTravelMileage;
    @BindView(R.id.tv_drivingFuel)
    TextView tvDrivingFuel;
    @BindView(R.id.tv_maxSpeed)
    TextView tvMaxSpeed;
    @BindView(R.id.tv_travelTime)
    TextView tvTravelTime;
    @BindView(R.id.tv_cost)
    TextView tvCost;

    public ItineraryViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_itinerary);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ObdTT data) {
        super.setData(data);
        tvAverageFuel.setText(data.getAverageFuel()+"L/100km");
        tvDrivingFuel.setText(data.getDrivingFuel()+"L");
        tvMaxSpeed.setText(data.getMaxSpeed()+"km/h");
        tvTravelMileage.setText(data.getTravelMileage()+"km");
        //TODO 修改cost值
        tvCost.setText("10元");
        tvTravelTime.setText(data.getTravelTime()+"分钟");
        tvStartTime.setText(NumberUtil.getTimeMillis(data.getStartTime()) + "");
        tvStopTime.setText(NumberUtil.getTimeMillis(data.getStopTime()) + "");
    }
}
