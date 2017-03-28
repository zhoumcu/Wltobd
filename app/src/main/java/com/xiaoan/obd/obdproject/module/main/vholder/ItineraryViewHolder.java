package com.xiaoan.obd.obdproject.module.main.vholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdTT;

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

    public ItineraryViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_itinerary);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(ObdTT data) {
        super.setData(data);
        tvStartTime.setText(data.getOccTime()+"");
        tvStopTime.setText(data.getTravelTime()+"");
    }
}
