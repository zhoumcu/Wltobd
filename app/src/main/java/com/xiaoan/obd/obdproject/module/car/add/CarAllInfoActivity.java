package com.xiaoan.obd.obdproject.module.car.add;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.data.BeamDataActivity;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.untils.AppManager;
import com.xiaoan.obd.obdproject.untils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/12 11:08
 * company: xxxx
 * email：1032324589@qq.com
 */
@RequiresPresenter(CarAllInfoPresenter.class)
public class CarAllInfoActivity extends BeamDataActivity<CarAllInfoPresenter, CarBean> {
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv_carname)
    TextView tvCarname;
    @BindView(R.id.tv_carserial)
    TextView tvCarserial;
    @BindView(R.id.tv_output)
    TextView tvOutput;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_gearbox)
    TextView tvGearbox;
    @BindView(R.id.tv_gear)
    TextView tvGear;
    @BindView(R.id.tv_fromtire)
    TextView tvFromtire;
    @BindView(R.id.tv_backtire)
    TextView tvBacktire;
    @BindView(R.id.tv_fueloil)
    TextView tvFueloil;
    @BindView(R.id.tv_fueloilprice)
    TextView tvFueloilprice;
    @BindView(R.id.tv_oilL)
    TextView tvOilL;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.btn_fromTyire)
    LinearLayout btnFromTyire;
    @BindView(R.id.btn_backTire)
    LinearLayout btnBackTire;
    @BindView(R.id.btn_oilType)
    LinearLayout btnOilType;
    @BindView(R.id.btn_oilPrice)
    LinearLayout btnOilPrice;
    @BindView(R.id.btn_oilWeight)
    LinearLayout btnOilWeight;
    public CarBean carBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_carinfo);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        AppManager.getAppManager().addActivity(this);
        btnFromTyire.setOnClickListener(view -> getPresenter().chooceFromTyre());
        btnBackTire.setOnClickListener(view -> getPresenter().chooceBackTyre());
        btnOilType.setOnClickListener(view -> getPresenter().chooceOilType());
        btnOilPrice.setOnClickListener(view -> getPresenter().chooceOilPrice());
        tvOilL.setOnClickListener(view -> getPresenter().chooceOilWeight());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            case R.id.menu_save:
                getPresenter().saveCarInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //调用inflate()方法创建菜单
        getMenuInflater().inflate(R.menu.car_info_menu,menu);
        //如果返回false，创建的菜单无法显示
        return true;
    }

    @Override
    public void setData(CarBean data) {
        super.setData(data);
        carBean = data;
        Logger.e(data.toString());
        tvCarname.setText(data.getCarTypeName());
        tvCarserial.setText(data.getCarSeriesName());
        tvOutput.setText(data.getDisplacement() + "mL");
        tvPublish.setText(data.getReleaseTime() + "年");
        tvWeight.setText(data.getWeight() + "KG");
        tvGearbox.setText(data.getDerailleur());
        tvGear.setText(data.getGears());
        tvFromtire.setText(data.getFtyre());
        tvBacktire.setText(data.getBtyre());
        tvFueloil.setText(data.getFuelType());
        tvFueloilprice.setText(data.getOilPrice());
        tvOilL.setText(data.getTank());
    }
}
