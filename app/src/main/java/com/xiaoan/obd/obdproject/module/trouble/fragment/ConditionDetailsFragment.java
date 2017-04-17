package com.xiaoan.obd.obdproject.module.trouble.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.beam.bijection.BeamFragment;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.module.trouble.TroubleCodeSearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Administrator on 2016/12/8 11:36
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ConditionDetailsFragment extends BeamFragment {
    private final static String TAG = ConditionDetailsFragment.class.getSimpleName();
    private View rootView;
    @BindView(R.id.tv_carname)
    TextView tvCarname;
    @BindView(R.id.tv_carserial)
    TextView tvCarserial;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.frg_condition, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @OnClick({R.id.btn_quickCheck, R.id.btn_performsCheck, R.id.tv_itinerary, R.id.tv_powerSys, R.id.tv_innerAirSys, R.id.tv_controlSys, R.id.tv_coolSys, R.id.tv_expert})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_quickCheck:
                break;
            case R.id.btn_performsCheck:
                break;
            case R.id.tv_itinerary:
                startActivity(new Intent(getActivity(), TroubleCodeSearchActivity.class));
                break;
            case R.id.tv_powerSys:
                PowerSysFragment.jumpIn((AppCompatActivity) getActivity());
                break;
            case R.id.tv_innerAirSys:
                InnerAirSysFragment.jumpIn((AppCompatActivity) getActivity());
                break;
            case R.id.tv_controlSys:
                ControlSysFragment.jumpIn((AppCompatActivity) getActivity());
                break;
            case R.id.tv_coolSys:
                CoolSysFragment.jumpIn((AppCompatActivity) getActivity());
                break;
            case R.id.tv_expert:
                break;
        }
    }
}
