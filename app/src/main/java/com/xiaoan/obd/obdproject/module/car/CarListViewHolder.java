package com.xiaoan.obd.obdproject.module.car;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;
import com.xiaoan.obd.obdproject.R;
import com.xiaoan.obd.obdproject.app.APP;
import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.module.car.add.CarAllInfoActivity;
import com.xiaoan.obd.obdproject.module.event.MessageEvent;
import com.xiaoan.obd.obdproject.module.mine.ObdBoxActivity;
import com.xiaoan.obd.obdproject.utils.BitmapUtils;
import com.xiaoan.obd.obdproject.utils.Constants;
import com.xiaoan.obd.obdproject.utils.SharedPreferences;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2016/12/12 10:48
 * company: xxxx
 * email：1032324589@qq.com
 */
public class CarListViewHolder extends BaseViewHolder<CarBean> {
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.tv_carname)
    TextView tvCarname;
    @BindView(R.id.tv_carserial)
    TextView tvCarserial;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.tv_obdBox)
    TextView tvObdBox;
    @BindView(R.id.tv_default)
    TextView tvDefault;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_currentStatic)
    TextView tvCurrentStatic;
    @BindView(R.id.tv_defaultStatic)
    TextView tvDefaultStatic;

    public CarListViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_carlist);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(CarBean data) {
        super.setData(data);
        tvCarserial.setText(data.getBrandCname());
        tvCarname.setText(data.getCarSeriesName());
        if (data.isDefault()) {
            tvDefaultStatic.setText("默认");
            tvDelete.setEnabled(false);
        }else {
            tvDefaultStatic.setText("");
            tvDelete.setEnabled(true);
        }
        if (data.isCurrent()) {
            tvCurrentStatic.setText("当前");
        }else {
            tvCurrentStatic.setText("");
        }
        tvObdBox.setOnClickListener(view -> setObdBox());
        tvInfo.setOnClickListener(view -> goCarInfo(data));
        tvDefault.setOnClickListener(view -> setDefault(data, data.getCarTypeId()));
        tvDelete.setOnClickListener(view -> sendDelete(data));
        tvChange.setOnClickListener(view -> goChange(data));
        imgCar.setImageBitmap(BitmapUtils.getImageFromAssetsFile(getContext(),"logo/"+data.getBrandLogo()+".png"));
    }

    private void goChange(CarBean data) {
        List<CarBean> carBeen = APP.getInstances().getDaoSession().getCarBeanDao().queryRaw(" WHERE IS_CURRENT = ?", new String[]{"1"});
        if (carBeen.size() > 0) {
            for (CarBean c : carBeen) {
                c.setIsCurrent(false);
                APP.getInstances().getDaoSession().getCarBeanDao().update(c);
            }
        }
        data.setIsCurrent(true);
        APP.getInstances().getDaoSession().getCarBeanDao().update(data);
        JUtils.Toast("当前切换成功！");
        MessageEvent event = new MessageEvent();
        event.setDataChange(true);
        event.setCurrentID(data.getUserCarID());
        EventBus.getDefault().post(event);
        broadcast();
    }

    private void setObdBox() {
        Intent intent = new Intent(getContext(), ObdBoxActivity.class);
        getContext().startActivity(intent);
    }

    private void goCarInfo(CarBean carData) {
        Intent intent = new Intent(getContext(), CarAllInfoActivity.class);
        intent.putExtra("id", carData);
        intent.putExtra(Constants.TYRE, Constants.INFO_DETAIL);
        getContext().startActivity(intent);
    }

    public void setDefault(CarBean data, long aDefault) {
        List<CarBean> carBeen = APP.getInstances().getDaoSession().getCarBeanDao().queryRaw(" WHERE IS_DEFAULT = ?", new String[]{"1"});
        if (carBeen.size() > 0) {
            for (CarBean c : carBeen) {
                c.setIsDefault(false);
                APP.getInstances().getDaoSession().getCarBeanDao().update(c);
            }
        }
        data.setIsDefault(true);
        APP.getInstances().getDaoSession().getCarBeanDao().update(data);
        JUtils.Toast("设置成功！");
        SharedPreferences.getInstance().putString(Constants.USER_CAR_ID,data.getUserCarID());
        MessageEvent event = new MessageEvent();
        event.setDataChange(true);
        event.setCurrentID(data.getUserCarID());
        EventBus.getDefault().post(event);
        broadcast();
    }

    public void sendDelete(CarBean data){
        Intent intent = new Intent(Constants.DELETE_LIST);
        intent.putExtra(Constants.ID,data.getUserCarID());
        intent.putExtra(Constants.CAR_OBJECT,data);
        getContext().sendBroadcast(intent);
    }

    public void setDelete(CarBean data) {
        APP.getInstances().getDaoSession().getCarBeanDao().delete(data);
        JUtils.Toast("删除成功！");
        broadcast();
    }
    private void broadcast(){
        Intent intent = new Intent(Constants.REFRESH_LIST);
        getContext().sendBroadcast(intent);
    }
}
