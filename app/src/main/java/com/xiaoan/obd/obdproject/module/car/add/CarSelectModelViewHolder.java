package com.xiaoan.obd.obdproject.module.car.add;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/13 13:41
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarSelectModelViewHolder extends BaseViewHolder<CarBean> {
    @BindView(R.id.tv_carmodle)
    TextView tvCarmodle;
    @BindView(R.id.tv_output)
    TextView tvOutput;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_gearbox)
    TextView tvGearbox;

    public CarSelectModelViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_carseries);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(CarBean data) {
        super.setData(data);
        tvCarmodle.setText(data.getBrandCname());
        tvOutput.setText("排量："+data.getDisplacement()+"L");
        tvPublish.setText("发布年份："+data.getReleaseTime()+"年");
        tvWeight.setText("整车质量："+data.getWeight()+"kg");
        tvGearbox.setText("变速箱类型："+data.getDerailleur());
    }
}
