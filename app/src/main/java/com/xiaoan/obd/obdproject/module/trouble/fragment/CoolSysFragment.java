package com.xiaoan.obd.obdproject.module.trouble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.xiaoan.obd.obdproject.R;

import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:36
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CoolSysFragment extends BeamFragment {
    private final static String TAG = CoolSysFragment.class.getSimpleName();
    private View rootView;

    public static CoolSysFragment newInstance(){
        return new CoolSysFragment();
    }
    public static void jumpIn(AppCompatActivity ac) {
        FragmentManager fragmentmanager = ac.getSupportFragmentManager();
        Fragment fragment = CoolSysFragment.newInstance();
        fragmentmanager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.containers, fragment, CoolSysFragment.TAG)
                .commitAllowingStateLoss();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.frg_coolsys, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
