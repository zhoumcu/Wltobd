package com.xiaoan.obd.obdproject.module.car.add;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/13 13:48
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarSeriesViewHolder extends BaseViewHolder<CarBean> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title)
    TextView title;

    public CarSeriesViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_carmodle);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(CarBean data) {
        super.setData(data);
        tvTitle.setText(data.getBrandCname());
        title.setText(data.getCount()+"个车型");
    }
}
