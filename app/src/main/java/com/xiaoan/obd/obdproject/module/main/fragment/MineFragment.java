package com.xiaoan.obd.obdproject.module.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.module.main.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author：Administrator on 2016/12/8 11:44
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(MineFragmentPresenter.class)
public class MineFragment extends BeamFragment<MineFragmentPresenter> {
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.btn_chart)
    LinearLayout btnChart;
    @BindView(R.id.tv_carCheck)
    LinearLayout tvCarCheck;
    @BindView(R.id.tv_searchcode)
    LinearLayout tvSearchcode;
    @BindView(R.id.tv_activity)
    LinearLayout tvActivity;
    public HomeActivity context;
    @BindView(R.id.tv_carname)
    TextView tvCarname;
    @BindView(R.id.tv_obdBox)
    TextView tvObdBox;
    @BindView(R.id.tv_businessCard)
    TextView tvBusinessCard;
    @BindView(R.id.tv_scanCode)
    TextView tvScanCode;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tv_obdConfig)
    LinearLayout tvObdConfig;
    @BindView(R.id.tv_obdpaired)
    LinearLayout tvObdpaired;
    private View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = (HomeActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.frg_mine, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvCar.setOnClickListener(view1 -> getPresenter().goCarActivity());
        tvCarCheck.setOnClickListener(view -> getPresenter().goConditionActivity());
        tvSearchcode.setOnClickListener(view -> getPresenter().goSearchActivity());
        tvActivity.setOnClickListener(view -> getPresenter().goAActivity());
        btnChart.setOnClickListener(view -> getPresenter().goChartActivity());
        profileImage.setOnClickListener(view -> getPresenter().goPersonInfoActivity());
        tvScanCode.setOnClickListener(view -> getPresenter().goScanCodeActivity());
        tvBusinessCard.setOnClickListener(view -> getPresenter().goBusinessCardActivity());
        tvObdBox.setOnClickListener(view -> getPresenter().goObdBoxActivity());
        tvObdConfig.setOnClickListener(view -> getPresenter().goObdConfigActivity());
        //tvObdpaired.setOnClickListener(view -> getPresenter().goObdPairedActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }
}
