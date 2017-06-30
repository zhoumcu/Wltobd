package com.xiaoan.obd.obdproject.module.travel;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.entity.TravelBean;
import com.xiaoan.obd.obdproject.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/9 15:51
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(TravelInfoPresenter.class)
public class TravelInfoActivity extends BeamDataActivity<TravelInfoPresenter, TravelBean> {

    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.tv_travelTime)
    TextView tvTravelTime;
    @BindView(R.id.tv_travelDis)
    TextView tvTravelDis;
    @BindView(R.id.tv_oilWeak)
    TextView tvOilWeak;
    @BindView(R.id.tv_avrOilWeak)
    TextView tvAvrOilWeak;
    @BindView(R.id.tv_avrOilWeakDel)
    TextView tvAvrOilWeakDel;
    @BindView(R.id.tv_sumSpace)
    TextView tvSumSpace;
    @BindView(R.id.tv_spaceOfKm)
    TextView tvSpaceOfKm;
    @BindView(R.id.tv_maxSpeed)
    TextView tvMaxSpeed;
    @BindView(R.id.tv_maxRevolution)
    TextView tvMaxRevolution;
    @BindView(R.id.tv_maxWaterTemp)
    TextView tvMaxWaterTemp;
    @BindView(R.id.tv_minWaterTemp)
    TextView tvMinWaterTemp;
    @BindView(R.id.tv_maxAcceleration)
    TextView tvMaxAcceleration;
    @BindView(R.id.tv_maxOpen)
    TextView tvMaxOpen;
    //    @BindView(R.id.spread_pie_chart1)
//    LineChart spreadPieChart1;
//    @BindView(R.id.spread_pie_chart2)
//    LineChart spreadPieChart2;
//    @BindView(R.id.spread_pie_chart3)
//    LineChart spreadPieChart3;
//    @BindView(R.id.spread_pie_chart4)
//    CombineChart spreadPieChart4;
//    private CurveData mCurveData = new CurveData();
//    private LineData mLineData = new LineData();
//    private ArrayList<ILineData> mDataList = new ArrayList<>();
//    private ArrayList<IBarLineCurveData> mBarDataList = new ArrayList<>();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    private ArrayList<PointF> mPointArrayList2 = new ArrayList<>();
    private ObdTT obdTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_travelinfo);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        obdTT = (ObdTT) getIntent().getSerializableExtra(Constants.OBD_TT_DATA);
        initData();
//        spreadPieChart1.setDataList(mDataList);
//        spreadPieChart2.setDataList(mDataList);
//        spreadPieChart3.setDataList(mDataList);
//        spreadPieChart4.setDataList(mBarDataList);
    }

    @Override
    public void setData(@Nullable TravelBean data) {
        super.setData(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initData() {
        tvAvrOilWeak.setText(obdTT.getAverageFuel()+"");
        tvAvrOilWeakDel.setText(obdTT.getAverageFuel()+"");
        tvMaxAcceleration.setText(obdTT.getMaxAcceleration()+"");
        tvMaxOpen.setText(obdTT.getMaxSpeed()+"");
        tvMaxRevolution.setText(obdTT.getMaxRpm()+"");
        tvMaxSpeed.setText(obdTT.getMaxSpeed()+"");
        tvMaxWaterTemp.setText(obdTT.getMaxTireTemperature()+"");
        tvMinWaterTemp.setText(obdTT.getMinTirePressure()+"");
        tvOilWeak.setText(obdTT.getAverageFuel()+"");
        tvSpaceOfKm.setText(obdTT.getSncode()+"");
        tvSumSpace.setText(obdTT.getTireState()+"");
        tvTravelDis.setText(obdTT.getTravelMileage()+"");
        tvTravelTime.setText(obdTT.getTravelTime()+"");
//        for (int i = 0; i < 8; i++) {
//            mPointArrayList.add(new PointF(points[i][0], points[i][1]));
//        }
//        mLineData.setValue(mPointArrayList);
//        mLineData.setColor(Color.MAGENTA);
//        mLineData.setPaintWidth(pxTodp(3));
//        mLineData.setTextSize(pxTodp(10));
//        mDataList.add(mLineData);
//        mBarDataList.add(mLineData);
//
//        for (int i = 0; i < 8; i++) {
//            mPointArrayList2.add(new PointF(points2[i][0], points2[i][1]));
//        }
//        mCurveData.setValue(mPointArrayList2);
//        mCurveData.setColor(Color.GREEN);
//        mCurveData.setPaintWidth(pxTodp(3));
//        mCurveData.setTextSize(pxTodp(10));
//        mBarDataList.add(mCurveData);


    }

    protected float[][] points = new float[][]{{1, 10}, {2, 47}, {3, 11}, {4, 38}, {5, 9}, {6, 52}, {7, 14}, {8, 37}, {9, 29}, {10, 31}};
    protected float[][] points2 = new float[][]{{1, 52}, {2, 13}, {3, 51}, {4, 20}, {5, 19}, {6, 20}, {7, 54}, {8, 7}, {9, 19}, {10, 41}};
    protected int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    protected float pxTodp(float value) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float valueDP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics);
        return valueDP;
    }
}
