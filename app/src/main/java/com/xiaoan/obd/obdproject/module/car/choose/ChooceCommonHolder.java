package com.xiaoan.obd.obdproject.module.car.choose;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/16 17:42
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ChooceCommonHolder extends BaseViewHolder<CarBean> {

    @BindView(R.id.title)
    TextView title;

    public ChooceCommonHolder(ViewGroup view) {
        super(view, R.layout.item_text);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(CarBean data) {
        super.setData(data);
        title.setText(data.getBrandCname());
    }
}
