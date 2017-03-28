package com.xiaoan.obd.obdproject.module.main.fragment;

import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.module.main.vholder.ItineraryViewHolder;

/**
 * author：Administrator on 2016/12/8 11:40
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ItineraryFragmentPresenter.class)
public class ItineraryFragment extends BeamListFragment<ItineraryFragmentPresenter,ObdTT>{

    @Override
    public BaseViewHolder<ObdTT> getViewHolder(ViewGroup parent, int viewType) {
        return new ItineraryViewHolder(parent);
    }

    @Override
    public int getLayout() {
        return R.layout.frg_itinerary;
    }

    @Override
    public ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(true)
                .setRefreshAble(true)
                .setNoMoreAble(true)
                .setErrorAble(true)
                .setErrorTouchToResumeAble(true);
    }
}
