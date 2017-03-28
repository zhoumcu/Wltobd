package com.xiaoan.obd.obdproject.module.trouble;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.FaultCodeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/1/17 15:09
 * company: xxxx
 * email：1032324589@qq.com
 */
public class TroubleVhold extends BaseViewHolder<FaultCodeBean> {
    @BindView(R.id.tv_troublecode)
    TextView tvTroublecode;
    @BindView(R.id.tv_troubleTitle)
    TextView tvTroubleTitle;
    @BindView(R.id.tv_troubleContent)
    TextView tvTroubleContent;

    public TroubleVhold(ViewGroup view) {
        super(view, R.layout.item_trouble);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(FaultCodeBean data) {
        super.setData(data);
        tvTroublecode.setText("故障编码：" + data.getCode());
        tvTroubleTitle.setText(data.getCdefine());
        tvTroubleContent.setText("内容：" + data.getContentZh());
    }
}
