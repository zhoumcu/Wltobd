package com.xiaoan.obd.obdproject.module.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.TravelBean;

import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TravelInfoPresenter.class)
public class TravelInfoActivity extends BeamDataActivity<TravelInfoPresenter,TravelBean>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_travelinfo);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(@Nullable TravelBean data) {
        super.setData(data);
    }

}
