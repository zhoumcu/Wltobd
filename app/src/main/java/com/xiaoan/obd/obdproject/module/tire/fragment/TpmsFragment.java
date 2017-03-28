package com.xiaoan.obd.obdproject.module.tire.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.widget.TireView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 10:28
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TpmsFragmentPresenter.class)
public class TpmsFragment extends BeamFragment<TpmsFragmentPresenter> {
    @BindView(R.id.tv_leftFrom)
    TireView tvLeftFrom;
    @BindView(R.id.tv_rightFrom)
    TireView tvRightFrom;
    @BindView(R.id.tv_leftBack)
    TireView tvLeftBack;
    @BindView(R.id.tv_rightBack)
    TireView tvRightBack;
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
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null)
            rootView = inflater.inflate(R.layout.frg_tiretpms, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }
}
