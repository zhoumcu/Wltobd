package com.xiaoan.obd.obdproject.module.main.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.czp.library.ArcProgress;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.xiaoan.obd.obdproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/8 11:42
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(MaintenanceFragmentPresenter.class)
public class MaintenanceFragment extends BeamFragment<MaintenanceFragmentPresenter> {

    @BindView(R.id.myProgress01)
    ArcProgress myProgress01;
    @BindView(R.id.myProgress02)
    ArcProgress myProgress02;
    @BindView(R.id.myProgress03)
    ArcProgress myProgress03;
    @BindView(R.id.btn_quickCheck)
    TextView btnQuickCheck;
    @BindView(R.id.btn_performsCheck)
    TextView btnPerformsCheck;
    @BindView(R.id.tv_itinerary)
    TextView tvItinerary;
    @BindView(R.id.tv_powerSys)
    TextView tvPowerSys;
    @BindView(R.id.tv_innerAirSys)
    TextView tvInnerAirSys;
    @BindView(R.id.tv_controlSys)
    TextView tvControlSys;
    @BindView(R.id.tv_coolSys)
    TextView tvCoolSys;
    @BindView(R.id.tv_expert)
    TextView tvExpert;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null)
            rootView = inflater.inflate(R.layout.frg_maintenance, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myProgress01.setOnCenterDraw(new ArcProgress.OnCenterDraw() {
            @Override
            public void draw(Canvas canvas, RectF rectF, float x, float y, float storkeWidth, int progress) {
                Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                textPaint.setStrokeWidth(60);
                textPaint.setColor(getResources().getColor(R.color.textColor));
                String progressStr = String.valueOf(progress + "%");
                float textX = x - (textPaint.measureText(progressStr) / 2);
                float textY = y - ((textPaint.descent() + textPaint.ascent()) / 2);
                canvas.drawText(progressStr, textX, textY, textPaint);
            }
        });
        myProgress01.setProgress(50);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
    }
}
