package com.xiaoan.obd.obdproject.module.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.beam.bijection.Presenter;
import com.xiaoan.obd.obdproject.entity.User;
import com.xiaoan.obd.obdproject.module.car.CarListActivity;
import com.xiaoan.obd.obdproject.module.mine.CodeActivity;
import com.xiaoan.obd.obdproject.module.mine.ObdBoxActivity;
import com.xiaoan.obd.obdproject.module.mine.PersonInfoActivity;
import com.xiaoan.obd.obdproject.module.obd.ObdPairedActivity;
import com.xiaoan.obd.obdproject.module.trouble.ConditionActivity;
import com.xiaoan.obd.obdproject.module.trouble.TroubleCodeSearchActivity;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

/**
 * author：Administrator on 2016/12/13 15:10
 * company: xxxx
 * email：1032324589@qq.com
 */
public class MineFragmentPresenter extends Presenter<MineFragment> {
    private User user;

    @Override
    protected void onCreate(@NonNull MineFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = SharedPreferences.getInstance().getUserInfo();
        getView().tvCarname.setText(user.getUserName());
    }

    public void goCarActivity() {
        getView().startActivity(new Intent(getView().getActivity(), CarListActivity.class));
    }
    public void goSearchActivity() {
        getView().startActivity(new Intent(getView().getActivity(), TroubleCodeSearchActivity.class));
    }
    public void goChartActivity() {
        getView().context.viewPager.setCurrentItem(3);
    }
    public void goConditionActivity() {
        getView().startActivity(new Intent(getView().getActivity(), ConditionActivity.class));
    }
    public void goAActivity() {
        getView().startActivity(new Intent(getView().getActivity(), ConditionActivity.class));
    }

    public void goPersonInfoActivity() {
        Intent intent = new Intent(getView().getActivity(), PersonInfoActivity.class);
        intent.putExtra("type", Constants.PersonInfo);
        getView().startActivity(intent);
    }

    public void goScanCodeActivity() {
        getView().startActivity(new Intent(getView().getActivity(), CodeActivity.class));
    }

    public void goBusinessCardActivity() {
        Intent intent = new Intent(getView().getActivity(), PersonInfoActivity.class);
        intent.putExtra("type", Constants.BusinessCard);
        getView().startActivity(intent);
    }

    public void goObdBoxActivity() {
        getView().startActivity(new Intent(getView().getActivity(), ObdBoxActivity.class));
    }

    public void goObdConfigActivity() {
        getView().startActivity(new Intent(getView().getActivity(), ObdPairedActivity.class));
    }

    public void goObdPairedActivity() {
        getView().startActivity(new Intent(getView().getActivity(), ObdPairedActivity.class));
    }
}
