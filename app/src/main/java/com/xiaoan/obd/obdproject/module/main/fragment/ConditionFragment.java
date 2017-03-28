package com.xiaoan.obd.obdproject.module.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;

import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:42
 * company: xxxx
 * email：1032324589@qq.com
 */
@Deprecated
@RequiresPresenter(ConditionFragmentPresenter.class)
public class ConditionFragment extends BeamFragment<ConditionFragmentPresenter> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_condition, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
