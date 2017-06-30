package com.xiaoan.obd.obdproject.module.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.module.base.ZhouListFragment;
import com.xiaoan.obd.obdproject.module.main.vholder.ItineraryViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:40
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(ItineraryFragmentPresenter.class)
public class ItineraryFragment extends ZhouListFragment<ItineraryFragmentPresenter, ObdTT> {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.img_right)
    ImageView imgRight;

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
                .setLoadmoreAble(false)
                .setRefreshAble(true)
                .setNoMoreAble(false)
                .setErrorAble(true)
                .setErrorTouchToResumeAble(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
